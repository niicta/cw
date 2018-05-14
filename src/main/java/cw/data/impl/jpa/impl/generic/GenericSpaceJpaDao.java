package cw.data.impl.jpa.impl.generic;

import cw.data.impl.jpa.AbstractJpaDao;
import cw.model.impl.generic.GenericSpace;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Stateless(name = "spaceDao", mappedName = "spaceDao")
@Local(cw.data.DAO.class)
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
