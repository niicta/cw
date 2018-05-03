package cw.data.impl.jpa;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import cw.data.DAO;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractJpaDao<T> implements DAO<T>
{
    @PersistenceContext(unitName = "cwPU")
    protected EntityManager entityManager;

    @Override
    public abstract T findById(int id);

    @Override
    public Collection<T> findAll(){
        return entityManager.findxa;
    }

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
