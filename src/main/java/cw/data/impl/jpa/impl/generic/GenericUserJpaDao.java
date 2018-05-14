package cw.data.impl.jpa.impl.generic;

import cw.data.impl.jpa.AbstractJpaDao;
import cw.model.User;
import cw.model.impl.generic.GenericUser;
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Stateless(name = "userDao", mappedName = "userDao")
@Local(cw.data.DAO.class)
public class GenericUserJpaDao extends AbstractJpaDao<User>
{
    private static final Logger LOG = Logger.getLogger(GenericUserJpaDao.class);
    @Override
    public User findById(int id){
        LOG.debug("find by id: " + id);
        return entityManager.find(GenericUser.class, id);
    }

    @Override
    public Collection<User> findAll(){
        TypedQuery<GenericUser> genericOrderTypedQuery = entityManager.createNamedQuery("GenericUser.findAll", GenericUser.class);
        Collection<User> usersToReturn = new ArrayList<User>();
        usersToReturn.addAll(genericOrderTypedQuery.getResultList());
        return usersToReturn;
    }

    @Override
    public void save(User user) {
        LOG.debug("saving user " + user.getId());
        super.save(user);
    }
}
