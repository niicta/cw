package cw.servlets.test;

import cw.data.DAO;
import cw.model.SpaceType;
import cw.model.Template;
import cw.model.User;
import cw.model.UserRole;
import cw.model.impl.generic.GenericTemplate;
import cw.model.impl.generic.GenericUser;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CreateTemplateObject extends HttpServlet {
    @EJB(beanName = "templateDao")
    DAO<Template> templateDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericTemplate genericTemplate = new GenericTemplate();

        genericTemplate.setBasePricePerHour(1.1);
        genericTemplate.setName("name");
        genericTemplate.setCountOfPlaces(1);
        genericTemplate.setFixed(true);
        genericTemplate.setFullWeek(true);
        genericTemplate.setSpaceType(SpaceType.MEETING_ROOM);

        templateDAO.save(genericTemplate);
        Collection<Template> templates = templateDAO.findAll();
        resp.getWriter().write("size - " + templates.size());
        for (Template template : templates){
            resp.getWriter().write("id - " + template.getId());
        }
    }
}
