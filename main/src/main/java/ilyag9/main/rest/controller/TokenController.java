package ilyag9.main.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ilyag9.main.service.user.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600000)
public class TokenController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/token",produces = "application/json")
	public String getToken(@RequestParam("username") final String username,
			@RequestParam("password") final String password) {
		return Optional.ofNullable(userService.login(username, password)).orElse("no token found");

	}
}
