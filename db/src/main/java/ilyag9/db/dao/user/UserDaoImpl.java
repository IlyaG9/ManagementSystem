package ilyag9.db.dao.user;

import ilyag9.db.dao.AbstractDao;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    public UserDaoImpl() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity get(String userName, String password) {
        Query query = getSession().getNamedQuery("User.findByloginAndPassword");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        return (UserEntity) query.uniqueResult();
    }

    @Override
    public UserEntity findByToken(String token) {
        Query query = getSession().getNamedQuery("User.findByToken");
        query.setParameter("token", token);
        return (UserEntity) query.uniqueResult();
    }
}
