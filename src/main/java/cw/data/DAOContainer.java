package cw.data;

import cw.model.Order;
import cw.model.Space;
import cw.model.Template;
import cw.model.Visit;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DAOContainer
{
    @EJB(beanName = "templateDao")
    private DAO<Template> templateDAO;
    @EJB(beanName = "spaceDao")
    private DAO<Space> spaceDao;
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;
    @EJB(beanName = "orderDao")
    private DAO<Order> orderDao;

    public DAO<Visit> getVisitDao() {
        return visitDao;
    }

    public void setVisitDao(DAO<Visit> visitDao) {
        this.visitDao = visitDao;
    }

    public DAO<Order> getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(DAO<Order> orderDao) {
        this.orderDao = orderDao;
    }

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
