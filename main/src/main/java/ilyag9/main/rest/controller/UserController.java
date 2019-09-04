package ilyag9.main.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ilyag9.main.rest.dto.UserDto;
import ilyag9.main.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {



	@Autowired
	private UserService userService;

	@GetMapping("list")
	private List<UserDto> list() {
		return userService.getAllUsers();

	}

	@GetMapping(value = "/get-user-by-token", produces = "application/json")
	public UserDto getUserByToken(@RequestParam("token") final String token) {
		return Optional.ofNullable(userService.getByToken(token)).map(user -> convert(user.get()))
				.orElse(new UserDto());
	}


	private UserDto convert(User user) {
		final UserDto result = new UserDto();

		result.setUserName(user.getUsername());
		result.setUserRoles(
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return result;
	}
	
	

}
