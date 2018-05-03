package cw.data.impl.jpa.impl.generic;

import cw.data.impl.jpa.AbstractJpaDao;
import cw.model.impl.generic.GenericUser;

import javax.persistence.TypedQuery;
import java.util.Collection;

public class GenericUserJpaDao extends AbstractJpaDao<GenericUser>
{
    @Override
    public GenericUser findById(int id){
        return entityManager.find(GenericUser.class, id);
    }

    @Override
    public Collection<GenericUser> findAll(){
        TypedQuery<GenericUser> genericOrderTypedQuery = entityManager.createNamedQuery("GenericUser.findAll", GenericUser.class);
        return genericOrderTypedQuery.getResultList();
    }
}
