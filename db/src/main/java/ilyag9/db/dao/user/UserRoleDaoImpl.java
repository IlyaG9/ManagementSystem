package ilyag9.db.dao.user;

import ilyag9.db.dao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<UserRoleEntity> implements UserRoleDao {
    public UserRoleDaoImpl() {
        super(UserRoleEntity.class);
    }
}
