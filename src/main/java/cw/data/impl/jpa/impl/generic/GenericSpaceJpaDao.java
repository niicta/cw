package cw.data.impl.jpa.impl.generic;

import cw.data.impl.jpa.AbstractJpaDao;
import cw.model.impl.generic.GenericSpace;

import javax.persistence.TypedQuery;
import java.util.Collection;

public class GenericSpaceJpaDao extends AbstractJpaDao<GenericSpace>
{
    @Override
    public GenericSpace findById(int id){
        return entityManager.find(GenericSpace.class, id);
    }

    @Override
    public Collection<GenericSpace> findAll(){
        TypedQuery<GenericSpace> genericOrderTypedQuery = entityManager.createNamedQuery("GenericSpace.findAll", GenericSpace.class);
        return genericOrderTypedQuery.getResultList();
    }
}
