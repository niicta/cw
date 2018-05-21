package cw.servlets.json.impl;

import com.google.gson.JsonElement;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Template;
import cw.model.User;
import cw.model.UserRole;
import cw.servlets.json.AbstractJsonServlet;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateOrderJsonServlet extends AbstractJsonServlet{
    private static final Logger LOG = Logger.getLogger(CreateOrderJsonServlet.class);
    static {
        LOG.debug("order json servlet is up");
    }

    private static final String TEMPLATE_ID = "template-id";
    private static final String USER_ID = "user-id";

    @Inject
    private User currentUser;
    private int templateId;
    private int userId;
    @EJB(beanName = "userDao")
    private DAO<User> userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            LOG.debug("create order requested");
            if (currentUser.getUserRole() == null)
                return;
            resp.setContentType("text/json;Charset=UTF-8");
            fillParametersFromRequest(req);
            ContextMap contextMap = buildCommandContext();
            JsonElement responseObject = buildResponse(contextMap);
            sendResponse(resp.getWriter(), responseObject);
        }
        catch (Exception e){
            LOG.debug("ex" , e);
            throw e;
        }
    }

    private void fillParametersFromRequest(HttpServletRequest req) {
        templateId = Integer.valueOf(req.getParameter(TEMPLATE_ID));
        if (null == req.getParameter(USER_ID) || "".equals(req.getParameter(USER_ID)) || UserRole.USER.equals(currentUser.getUserRole())){
            userId = currentUser.getId();
        }
        else {
            userId = Integer.valueOf(req.getParameter(USER_ID));
        }
        LOG.debug(String.format("params: user id %d template id %d ", userId, templateId));
    }

    private ContextMap buildCommandContext() {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE, ControllerCommandConstants.CREATE_ORDER_COMMAND_VALUE);
        contextMap.put(ControllerCommandConstants.USER_ID_ATTRIBUTE, userId);
        contextMap.put(ControllerCommandConstants.TEMPLATE_ID_ATTRIBUTE, templateId);
        return contextMap;
    }
}
