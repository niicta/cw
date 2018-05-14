package cw.servlets.json.impl;

import com.google.gson.JsonElement;
import cw.controller.command.ControllerCommandConstants;
import cw.servlets.json.AbstractJsonServlet;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginJsonServlet extends AbstractJsonServlet {
    private static final Logger LOG = Logger.getLogger(LoginJsonServlet.class);
    static {
        LOG.debug("login json servlet is up");
    }

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;Charset=UTF-8");
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        LOG.debug("login json is requested for login " + login);
        ContextMap contextMap = buildCommandContext(login, password);
        JsonElement responseObject = buildResponse(contextMap);
        sendResponse(resp.getWriter(), responseObject);
    }

    private ContextMap buildCommandContext(String login, String password) {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE, ControllerCommandConstants.LOGIN_COMMAND_VALUE);
        contextMap.put(ControllerCommandConstants.USER_LOGIN_ATTRIBUTE, login);
        contextMap.put(ControllerCommandConstants.USER_PASSWORD_ATTRIBUTE, password);
        return contextMap;
    }
}
