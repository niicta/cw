package cw.data.impl.jpa.impl.generic;

import cw.data.impl.jpa.AbstractJpaDao;
import cw.model.impl.generic.GenericVisit;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Stateless(name = "visitDao", mappedName = "visitDao")
@Local(cw.data.DAO.class)
public class GenericVisitJpaDao extends AbstractJpaDao<GenericVisit>
{
    @Override
    public GenericVisit findById(int id){
        return entityManager.find(GenericVisit.class, id);
    }

    @Override
    public Collection<GenericVisit> findAll(){
        TypedQuery<GenericVisit> genericOrderTypedQuery = entityManager.createNamedQuery("GenericVisit.findAll", GenericVisit.class);
        return genericOrderTypedQuery.getResultList();
    }
}
