package ilyag9.main.rest.dto;

import java.util.List;

public class SensorParamDto {

	private Long id;
	private String name;
	private List<SensorParamDto> childs;
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
	public List<SensorParamDto> getChilds() {
		return childs;
	}
	public void setChilds(List<SensorParamDto> childs) {
		this.childs = childs;
	}
	
	

}
