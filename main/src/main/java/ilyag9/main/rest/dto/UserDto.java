package ilyag9.main.rest.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4689094334269151441L;
	private String userName;
	private List<String> userRoles;
	private Boolean isTokenPresent;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public Boolean getIsTokenPresent() {
		return isTokenPresent;
	}

	public void setIsTokenPresent(Boolean isTokenPresent) {
		this.isTokenPresent = isTokenPresent;
	}



}
