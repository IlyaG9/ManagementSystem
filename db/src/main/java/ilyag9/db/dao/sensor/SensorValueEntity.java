package ilyag9.db.dao.sensor;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="sensor_value")
public class SensorValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="SENSOR_ID", referencedColumnName = "ID")
    private SensorEntity sensor;
    
    @ManyToOne
    @JoinColumn(name="PARAM_ID", referencedColumnName = "ID")
    private SensorParamEntity param;

    @Column(name = "VALUE")
    private String value;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensorEntity getSensor() {
        return sensor;
    }

    public void setSensor(SensorEntity sensor) {
        this.sensor = sensor;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public SensorParamEntity getParam() {
		return param;
	}

	public void setParam(SensorParamEntity param) {
		this.param = param;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
    
}
