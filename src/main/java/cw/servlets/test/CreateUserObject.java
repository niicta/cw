package cw.servlets.test;

import cw.data.DAO;
import cw.model.User;
import cw.model.UserRole;
import cw.model.impl.generic.GenericUser;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CreateUserObject extends HttpServlet {
    @EJB(beanName = "userDao")
    DAO<User> userDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericUser genericUser = new GenericUser();
        genericUser.setId(0);
        genericUser.setEmail("sample");
        genericUser.setName("name");
        genericUser.setPhone("1");
        genericUser.setUserRole(UserRole.ADMIN);
        userDAO.save(genericUser);
        Collection<User> users = userDAO.findAll();
        resp.getWriter().write("size - " + users.size());
        for (User user : users){
            resp.getWriter().write("id - " + user.getId());
        }
    }
}
