package cw.data.impl.jpa;

import cw.data.DAO;
import cw.data.impl.jpa.impl.generic.GenericUserJpaDao;
import org.apache.log4j.Logger;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.REQUIRED;

@TransactionManagement(value= TransactionManagementType.BEAN)
@TransactionAttribute(value=REQUIRED)
public abstract class AbstractJpaDao<T> implements DAO<T>
{
    private static final Logger LOG = Logger.getLogger(AbstractJpaDao.class);
    static{
        Locale.setDefault(Locale.ENGLISH);
    }

    @PersistenceContext(unitName = "cwPU")
    protected EntityManager entityManager;

    @Override
    public abstract T findById(int id);

    @Override
    public abstract Collection<T> findAll();

    @Override
    public void save(T t){
        LOG.debug("saving " + t);
        entityManager.persist(t);
    }

    @Override
    public void update(T t){
        entityManager.merge(t);
    }

    @Override
    public void delete(T t){
        delete(Collections.singletonList(t));
    }

    @Override
    public void delete(Collection<T> collection){
        for (T t : collection){
            entityManager.remove(t);
        }
    }

}
