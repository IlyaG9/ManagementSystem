package ilyag9.db.dao.user;

import ilyag9.db.dao.Dao;

public interface UserDao extends Dao<UserEntity> {

    UserEntity get(String userName,String password);

    UserEntity findByToken(String token);
}
