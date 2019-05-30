package sensors;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ilyag9.db.dao.sensor.SensorDao;
import ilyag9.db.dao.sensor.SensorEntity;
import ilyag9.db.dao.sensor.SensorParamEntity;
import ilyag9.db.dao.sensor.SensorValueDao;

@RunWith(MockitoJUnitRunner.class)
public class RequestTemperatureSensorDataTest {

	private static final Logger LOGGER = Logger.getLogger(RequestTemperatureSensorDataTest.class);

	@Mock
	private SensorDao sensorDao;
	
	@Mock
	private SensorValueDao sensorValueDao;

	@InjectMocks
	private RequestTemperatureSensorData requestTemperatureSensorData=new RequestTemperatureSensorData();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void requestTest() {

		final SensorEntity entity = new SensorEntity();
		entity.setId(1l);
		entity.setName("Test");
		entity.setUrl("http://192.168.0.196/");
		
		SensorParamEntity paramHall=new SensorParamEntity();
		paramHall.setName("hall");

		SensorParamEntity paramBedroom=new SensorParamEntity();
		paramBedroom.setName("bedroom");

		SensorParamEntity paramStreet=new SensorParamEntity();
		paramStreet.setId(1L);
		paramStreet.setName("street");
		
		SensorParamEntity paramTemp=new SensorParamEntity();
		paramTemp.setName("temperature");
		paramTemp.setParent(paramStreet);
		
		SensorParamEntity paramHum=new SensorParamEntity();
		paramHum.setName("humidity");
		paramHum.setParent(paramStreet);
		
		
		paramStreet.setChilds(Arrays.asList(paramTemp,paramHum));
		
		entity.setParams(Arrays.asList(paramHall,paramBedroom,paramStreet));
		
		Mockito.when(sensorDao.findAll()).thenReturn(Arrays.asList(entity));

		
		requestTemperatureSensorData.request();
		
	}

}
