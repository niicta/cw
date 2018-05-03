package cw.data.impl.jpa;

import cw.data.DAO;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractJpaDao<T> implements DAO<T>
{
    @PersistenceContext(unitName = "cwPU")
    protected EntityManager entityManager;

    @Override
    public abstract T findById(int id);

    @Override
    public abstract Collection<T> findAll();

    @Override
    public void save(T t){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(t);
        entityTransaction.commit();
    }

    @Override
    public void update(T t){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.refresh(t);
        entityTransaction.commit();
    }

    @Override
    public void delete(T t){
        delete(Collections.singletonList(t));
    }

    @Override
    public void delete(Collection<T> collection){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        for (T t : collection){
            entityManager.remove(t);
        }
        entityTransaction.commit();
    }

    @PreDestroy
    private void closeEntityManager(){
        entityManager.close();
    }
}
