package cw.data.impl.jpa.impl.generic;

import cw.data.impl.jpa.AbstractJpaDao;
import cw.model.Template;
import cw.model.impl.generic.GenericTemplate;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Stateless(name = "templateDao", mappedName = "templateDao")
@Local(cw.data.DAO.class)
public class GenericTemplateJpaDao extends AbstractJpaDao<GenericTemplate>
{
    @Override
    public GenericTemplate findById(int id){
        return entityManager.find(GenericTemplate.class, id);
    }

    @Override
    public Collection<GenericTemplate> findAll(){
        TypedQuery<GenericTemplate> genericOrderTypedQuery = entityManager.createNamedQuery("GenericTemplate.findAll", GenericTemplate.class);
        return genericOrderTypedQuery.getResultList();
    }
}
