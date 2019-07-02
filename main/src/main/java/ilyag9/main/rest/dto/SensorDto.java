package ilyag9.main.rest.dto;

import java.util.Date;
import java.util.List;

public class SensorDto {

	private Long id;

	private String name;

	private String mac;

	private String url;

	private String description;

	private Date createDate;
	
	private List<SensorParamDto> paramList;

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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<SensorParamDto> getParamList() {
		return paramList;
	}

	public void setParamList(List<SensorParamDto> paramList) {
		this.paramList = paramList;
	}
	
	
	

}
