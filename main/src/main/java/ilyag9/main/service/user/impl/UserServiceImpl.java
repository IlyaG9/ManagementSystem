package ilyag9.main.service.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ilyag9.db.dao.user.UserDao;
import ilyag9.db.dao.user.UserEntity;
import ilyag9.db.dao.user.UserRoleEntity;
import ilyag9.main.rest.dto.UserDto;
import ilyag9.main.service.user.UserService;

@Service("userService")
@PropertySource("classpath:application.properties")
public class UserServiceImpl implements UserService {

	@Value("${token.live.time}")
	private Long tokenLiveTime;
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public String login(String userName, String password) {
		return Optional.ofNullable(userDao.get(userName, password)).filter(Objects::nonNull).map(this::checkToken)
				.map(UserEntity::getToken).orElse(null);
	}

	@Transactional
	private UserEntity checkToken(final UserEntity entity) {
		if (entity.getLastLogin() == null || (new Date().getTime() - entity.getLastLogin().getTime()) > tokenLiveTime) {
			entity.setToken(UUID.randomUUID().toString());
			entity.setLastLogin(new Date());
			userDao.update(entity);
		}
		return entity;
	}

	@Override
	@Transactional
	public Optional<User> getByToken(String token) {
		return Optional.ofNullable(userDao.findByToken(token)).filter(Objects::nonNull).map(this::convert);
	}

	private User convert(UserEntity entity) {
		boolean notExpired = (new Date().getTime() - entity.getLastLogin().getTime()) <= tokenLiveTime;
		final User user = new User(entity.getLogin(), entity.getPassword(), notExpired, notExpired, notExpired,
				notExpired, AuthorityUtils.createAuthorityList(entity.getRoleList().stream()
						.map(UserRoleEntity::getIdentifier).collect(Collectors.toList()).toArray(new String[0])));

		return user;
	}

	@Override
	@Transactional
	public List<UserDto> getAllUsers() {

		return Optional.ofNullable(userDao.findAll()).map(this::convert).orElse(new ArrayList<UserDto>());
	}

	private List<UserDto> convert(Collection<UserEntity> listEntity) {
		return listEntity.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private UserDto convertToDto(UserEntity entity) {
		final UserDto result = new UserDto();

		if (entity.getRoleList() != null) {
			result.setUserRoles(
					entity.getRoleList().stream().map(UserRoleEntity::getIdentifier).collect(Collectors.toList()));
		}
		result.setUserName(entity.getLogin());
		result.setIsTokenPresent(!StringUtils.isEmpty(entity.getToken()));

		return result;
	}
}
