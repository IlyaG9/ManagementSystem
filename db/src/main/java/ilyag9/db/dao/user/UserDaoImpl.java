package ilyag9.db.dao.user;

import ilyag9.db.dao.AbstractDao;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    public UserDaoImpl() {
        super(UserEntity.class);
    }
}
