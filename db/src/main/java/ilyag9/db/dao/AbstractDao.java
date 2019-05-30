package ilyag9.db.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractDao<T> implements Dao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    private final Class<T> clazz;

    public AbstractDao(Class<T> clazz){
        this.clazz=clazz;
    }
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void create(T object) {
        if (object != null) {
            getSession().save(object);
        }
    }

    public T get(Serializable id) {
        return (T) getSession().get(clazz, id);
    }

    public Collection<T> findAll(){

        Criteria crit = getSession().createCriteria(clazz);
        return crit.list();
    }

}
