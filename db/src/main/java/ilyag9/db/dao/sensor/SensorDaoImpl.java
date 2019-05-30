package ilyag9.db.dao.sensor;

import org.springframework.stereotype.Repository;
import ilyag9.db.dao.AbstractDao;

@Repository("sensorDao")
public class SensorDaoImpl extends AbstractDao<SensorEntity> implements SensorDao {
    public SensorDaoImpl() {
        super(SensorEntity.class);
    }
}
