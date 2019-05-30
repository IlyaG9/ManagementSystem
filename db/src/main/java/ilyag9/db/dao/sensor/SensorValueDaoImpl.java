package ilyag9.db.dao.sensor;

import org.springframework.stereotype.Repository;

import ilyag9.db.dao.AbstractDao;

@Repository("sensorValueDao")
public class SensorValueDaoImpl extends AbstractDao<SensorValueEntity> implements SensorValueDao{

	public SensorValueDaoImpl() {
		super(SensorValueEntity.class);
	}
}
