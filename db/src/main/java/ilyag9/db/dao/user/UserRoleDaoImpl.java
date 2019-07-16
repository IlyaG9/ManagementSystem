package ilyag9.db.dao.user;

import ilyag9.db.dao.AbstractDao;

public class UserRoleDaoImpl extends AbstractDao<UserRoleEntity> implements UserRoleDao {
    public UserRoleDaoImpl() {
        super(UserRoleEntity.class);
    }
}
