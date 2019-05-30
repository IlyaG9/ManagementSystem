package ilyag9.db.dao.sensor;

import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name="sensor_param")
public class SensorParamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="PARENT_ID", referencedColumnName = "ID")
    private SensorParamEntity parent;

    @ManyToOne
    @JoinColumn(name="SENSOR_ID", referencedColumnName = "ID")
    private SensorEntity sensor;

    @Column(name = "NAME")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PARENT_ID")
    @Lazy
    private List<SensorParamEntity> childs;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public SensorParamEntity getParent() {
		return parent;
	}

	public void setParent(SensorParamEntity parent) {
		this.parent = parent;
	}

	public List<SensorParamEntity> getChilds() {
		return childs;
	}

	public void setChilds(List<SensorParamEntity> childs) {
		this.childs = childs;
	}
    
    
}
