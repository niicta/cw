package cw.data;

import cw.model.Space;
import cw.model.Template;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DAOContainer
{
    @EJB(beanName = "templateDao")
    private DAO<Template> templateDAO;
    @EJB(beanName = "spaceDao")
    private DAO<Space> spaceDao;


    public DAO<Template> getTemplateDAO(){
        return templateDAO;
    }

    public void setTemplateDAO(DAO<Template> templateDAO){
        this.templateDAO = templateDAO;
    }

    public DAO<Space> getSpaceDao() {
        return spaceDao;
    }

    public void setSpaceDao(DAO<Space> spaceDao) {
        this.spaceDao = spaceDao;
    }
}
