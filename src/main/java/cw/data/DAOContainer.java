package cw.data;

import cw.model.Template;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DAOContainer
{
    @EJB(beanName = "templateDao")
    private DAO<Template> templateDAO;

    public DAO<Template> getTemplateDAO(){
        return templateDAO;
    }

    public void setTemplateDAO(DAO<Template> templateDAO){
        this.templateDAO = templateDAO;
    }
}
