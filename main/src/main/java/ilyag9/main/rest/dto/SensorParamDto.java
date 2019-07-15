package ilyag9.main.rest.dto;

import java.util.List;

public class SensorParamDto {

	private Long id;
	private String name;
	private List<SensorParamDto> childsParams;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SensorParamDto> getChildsParams() {
		return childsParams;
	}
	public void setChildsParams(List<SensorParamDto> childsParams) {
		this.childsParams = childsParams;
	}

}
