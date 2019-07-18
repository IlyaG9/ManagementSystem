package ilyag9.main.rest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ilyag9.db.dao.user.UserDao;
import ilyag9.db.dao.user.UserEntity;
import ilyag9.main.rest.dto.UserDto;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("list")
	private List<UserDto> list() {
		return Optional.ofNullable(userDao.findAll()).map(this::convert).orElse(new  ArrayList<UserDto>());

	}

	private List<UserDto> convert(Collection<UserEntity> listEntity) {
		return listEntity.stream().map(this::convert).collect(Collectors.toList());
	}

	private UserDto convert(UserEntity entity) {
		final UserDto dto = new UserDto();

		BeanUtils.copyProperties(entity,dto );
		dto.setIsTokenPresent(!StringUtils.isEmpty(entity.getToken()));

		return dto;
	}

}
