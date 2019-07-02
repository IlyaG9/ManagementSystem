package ilyag9.main.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ilyag9.db.dao.sensor.SensorDao;
import ilyag9.db.dao.sensor.SensorEntity;
import ilyag9.db.dao.sensor.SensorParamEntity;
import ilyag9.main.rest.dto.SensorDto;
import ilyag9.main.rest.dto.SensorParamDto;

@RestController
@RequestMapping("/sensor")
public class SensorController {

	
	private static final Logger LOGGER = Logger.getLogger(SensorController.class);
	@Autowired
	private SensorDao sensorDao;

	@GetMapping("/list")
	public List<SensorDto> list() {
		return sensorDao.findAll().parallelStream().map(this::convert).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public SensorDto get(@RequestParam(value = "id", required = true) Long id) {
		return Optional.ofNullable(sensorDao.get(id)).map(this::convert).orElse(null);
	}

	@PostMapping("save")
	@Transactional
	public void save(@RequestBody SensorDto sensor) {

		Assert.notNull(sensor, "Sensor not set");
		Assert.notNull(sensor.getName(), "Sensor name not set");
		Assert.notNull(sensor.getUrl(), "Sensor url not set");

		final SensorEntity entity = new SensorEntity();
		entity.setCreateDate(new Date());
		BeanUtils.copyProperties(sensor, entity);

		sensorDao.create(entity);

		
		LOGGER.info("Created new sensor "+sensor.getName());
	}

	private SensorDto convert(SensorEntity entity) {
		final SensorDto sensor = new SensorDto();

		BeanUtils.copyProperties(entity, sensor);

		if (entity.getParams() != null) {
			sensor.setParamList(entity.getParams().parallelStream().map(this::convert).collect(Collectors.toList()));
		}

		return sensor;
	}

	private SensorParamDto convert(SensorParamEntity paramEntity) {
		final SensorParamDto paramDto = new SensorParamDto();
		BeanUtils.copyProperties(paramEntity, paramDto);
		return paramDto;
	}
}