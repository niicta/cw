package cw.data.impl.jpa.impl.generic;

import cw.data.impl.jpa.AbstractJpaDao;
import cw.model.impl.generic.GenericOrder;

import javax.persistence.TypedQuery;
import java.util.Collection;

public class GenericOrderJpaDao extends AbstractJpaDao<GenericOrder>
{
    @Override
    public GenericOrder findById(int id){
        return entityManager.find(GenericOrder.class, id);
    }

    @Override
    public Collection<GenericOrder> findAll(){
        TypedQuery<GenericOrder> genericOrderTypedQuery = entityManager.createNamedQuery("GenericOrder.findAll", GenericOrder.class);
        return genericOrderTypedQuery.getResultList();
    }
}
