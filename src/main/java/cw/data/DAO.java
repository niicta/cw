package cw.data;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Collection;

    public interface DAO<T>
    {
        T findById(int id);
        Collection<T> findAll();
        void save(T t);
        void update(T t);
        void delete(T t);
        void delete(Collection <T> tCollection);
    }
