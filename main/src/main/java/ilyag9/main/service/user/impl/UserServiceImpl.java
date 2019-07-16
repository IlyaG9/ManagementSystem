package ilyag9.main.service.user.impl;

import ilyag9.db.dao.user.UserDao;
import ilyag9.db.dao.user.UserEntity;
import ilyag9.main.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service("userService")
@PropertySource("classpath:application.properties")
public class UserServiceImpl implements UserService {

    @Value("${token.live.time}")
    private Long tokenLiveTime;
    @Autowired
    private UserDao userDao;

    @Override

    public String login(String userName, String password) {
        return Optional.ofNullable(userDao.get(userName,password)).filter(Objects::nonNull).map(this::checkToken).map(UserEntity::getToken).orElse(null);
    }
    @Transactional
    private UserEntity checkToken(final UserEntity entity){
        if((new Date().getTime()-entity.getLastLogin().getTime())>tokenLiveTime){
            entity.setToken(UUID.randomUUID().toString());
            entity.setLastLogin(new Date());
            userDao.update(entity);
        }
        return entity;
    }
}
