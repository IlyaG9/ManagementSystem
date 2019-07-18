package ilyag9.main.rest.dto;

import java.util.Date;

public class UserDto {

    private Long id;

    private String name;

    private String login;

    private Date lastLogin;

    private Date createDate;
    
    private Boolean isTokenPresent;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getIsTokenPresent() {
		return isTokenPresent;
	}

	public void setIsTokenPresent(Boolean isTokenPresent) {
		this.isTokenPresent = isTokenPresent;
	}
    
    
	
}
