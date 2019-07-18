package ilyag9.main.service.user.impl;

import java.util.Date;
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

import ilyag9.db.dao.user.UserDao;
import ilyag9.db.dao.user.UserEntity;
import ilyag9.db.dao.user.UserRoleEntity;
import ilyag9.main.service.user.UserService;

@Service("userService")
@PropertySource("classpath:application.properties")
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${token.live.time}")
    private Long tokenLiveTime;
    @Autowired
    private UserDao userDao;

    @Override
    public String login(String userName, String password) {
        return Optional.ofNullable(userDao.get(userName,password)).filter(Objects::nonNull).map(this::checkToken).map(UserEntity::getToken).orElse(null);
    }

    private UserEntity checkToken(final UserEntity entity){
        if(entity.getLastLogin()==null||(new Date().getTime()-entity.getLastLogin().getTime())>tokenLiveTime){
            entity.setToken(UUID.randomUUID().toString());
            entity.setLastLogin(new Date());
            userDao.update(entity);
        }
        return entity;
    }
	
    @Override
	public Optional<User> getByToken(String token) {
		return Optional.ofNullable(userDao.findByToken(token)).filter(Objects::nonNull).map(this::convert);
	}
	
	private User convert(UserEntity entity) {
		boolean notExpired = !((new Date().getTime()-entity.getLastLogin().getTime())>tokenLiveTime);
		final User user = new User(entity.getLogin(), entity.getPassword(), notExpired, notExpired, notExpired,
				notExpired, AuthorityUtils.createAuthorityList(entity.getRoleList().stream()
						.map(UserRoleEntity::getIdentifier).collect(Collectors.toList()).toArray(new String[0])));

		return user;
	}
}
