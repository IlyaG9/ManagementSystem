package sensors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ilyag9.db.dao.sensor.SensorDao;
import ilyag9.db.dao.sensor.SensorEntity;
import ilyag9.db.dao.sensor.SensorParamEntity;
import ilyag9.db.dao.sensor.SensorValueDao;
import ilyag9.db.dao.sensor.SensorValueEntity;
import org.springframework.util.StringUtils;

@Component
@PropertySource("classpath:sensors.properties")
public class RequestTemperatureSensorData {

	private static final Logger LOGGER = Logger.getLogger(RequestTemperatureSensorData.class);

	@Autowired
	private SensorDao sensorDao;

	@Autowired
	private SensorValueDao sensorValueDao;

	private final JsonParser jsonParser = new JsonParser();

	@Scheduled(fixedRateString = "${request.sensor.milliseconds}")
	@Transactional
	public void request() {
		LOGGER.info("Start request temperature data");

		Collection<SensorEntity> sensors = sensorDao.findAll();
		LOGGER.info("Found " + sensors.size() + " sensors");

		for (SensorEntity sensor : sensors) {
			LOGGER.info("Start process sensor with id " + sensor.getId());
			String jsonStr = getJson(sensor.getUrl());
			LOGGER.info("Recieved: " + jsonStr);
			if (StringUtils.isEmpty(jsonStr) == false) {
				JsonObject jsonObj = (JsonObject) jsonParser.parse(jsonStr);

				saveParams(sensor.getParams(), jsonObj, sensor);
			}
		}

	}

	private void saveParams(List<SensorParamEntity> params, JsonObject jsonObj,SensorEntity sensor) {
		if(params==null){
			return;
		}
		for (SensorParamEntity param : params) {
			JsonElement element = jsonObj.get(param.getName());
			if (element.isJsonPrimitive()) {
				String value = element.getAsString();
				if (value != null) {
					SensorValueEntity valueEntity = new SensorValueEntity();
					valueEntity.setParam(param);
					valueEntity.setValue(value);
					valueEntity.setCreateDate(new Date());
					valueEntity.setSensor(sensor);
					sensorValueDao.create(valueEntity);
					LOGGER.info("Created " + param.getName() + " with value " + value);
				}
			}else if(element.isJsonObject()) {
				saveParams(param.getChilds(),element.getAsJsonObject(),sensor);
			}
			
		}
	}

	public String getJson(String url) {
		LOGGER.info("Try load url " + url);
		final HttpGet httpget = new HttpGet(url);
		httpget.setHeader(new BasicHeader("Prama", "no-cache"));
		httpget.setHeader(new BasicHeader("Cache-Control", "no-cache"));
		final RequestConfig params = RequestConfig.custom().setConnectTimeout(10 * 1000).build();
		httpget.setConfig(params);

		HttpResponse response = null;
		try {
			response = getClient().execute(httpget);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				LOGGER.warn("for url " + url + " return status code " + statusCode);
				return null;
			}
		} catch (IOException e) {
			LOGGER.warn("Can not execute url " + url, e);
			return null;
		}

		final ByteArrayOutputStream bais = new ByteArrayOutputStream();
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				try {
					IOUtils.copy(is, bais);
				} catch (Exception e) {
					LOGGER.warn("Error save remote file", e);
				} finally {
					is.close();
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Can't donwload torrent " + url, e);
		} finally {
			if (response != null) {
				EntityUtils.consumeQuietly(response.getEntity());
			}
		}

		return bais.toString();
	}

	public CloseableHttpClient getClient() {
		HttpClientConnectionManager connectionManager = null;

		try {
			connectionManager = new PoolingHttpClientConnectionManager();
		} catch (Throwable e) {
			LOGGER.warn("Can not create client", e);
		}

		return HttpClients.custom().setConnectionManager(connectionManager).build();
	}

	public static void main(String[] a) {
		RequestTemperatureSensorData obj = new RequestTemperatureSensorData();
		String json = obj.getJson("http://ilyag9.asuscomm.com:196/");
		LOGGER.info(json);

	}
}
