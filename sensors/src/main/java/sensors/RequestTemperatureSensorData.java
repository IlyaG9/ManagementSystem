package sensors;

import ilyag9.db.dao.sensor.SensorDao;
import ilyag9.db.dao.sensor.SensorEntity;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@EnableScheduling
public class RequestTemperatureSensorData {

    private static final Logger LOGGER = Logger.getLogger(RequestTemperatureSensorData.class);

    @Autowired
    private SensorDao sensorDao;

    @Scheduled(fixedRate = 5000)
    public void request() {
        try {
            LOGGER.info("Start request temperature data");

            Collection<SensorEntity> sensors = sensorDao.findAll();
            LOGGER.info("Found " + sensors.size() + " sensors");


            for (SensorEntity sensor : sensors) {
                LOGGER.info("Start process sensor with id " + sensor.getId());
                String jsonStr = getJson(sensor.getUrl());

            }

        } catch (Exception e) {

            LOGGER.error("Error request temperature sensor", e);

        }

    }

    public String getJson(String url) {
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
}
