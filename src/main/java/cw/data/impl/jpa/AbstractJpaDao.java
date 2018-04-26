package cw.data.impl.jpa;

import cw.data.DAO;

import javax.persistence.PersistenceUnit;
import java.util.Collection;

public abstract class AbstractJpaDao<T> implements DAO<T>
{
    @Override
    public abstract T findById(int id);

    @Override
    public Collection<T> findAll(){
        return null;
    }

    @Override
    public void save(T t){

    }

    @Override
    public void update(T t){

    }

    @Override
    public void delete(T t){

    }

    @Override
    public void delete(Collection<T> collection){

    }
}
