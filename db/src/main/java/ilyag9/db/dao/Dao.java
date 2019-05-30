package ilyag9.db.dao;

import java.io.Serializable;
import java.util.Collection;

public interface Dao<T> {

    void create(T object);

    T get(Serializable id);

    Collection<T> findAll();
}
