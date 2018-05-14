package cw.servlets.json.impl;

import com.google.gson.JsonElement;
import cw.controller.command.ControllerCommandConstants;
import cw.model.User;
import cw.servlets.json.AbstractJsonServlet;
import cw.utils.context.ContextMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CurrentUserJsonServlet extends AbstractJsonServlet {
    @Inject
    private User user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF8");
        resp.setContentType("text/json;");
        ContextMap contextMap = buildCommandContext();
        JsonElement responseObject = buildResponseJsonByResult(contextMap);
        sendResponse(resp.getWriter(), responseObject);
    }

    private ContextMap buildCommandContext(){
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.USER_ATTRIBUTE, user);
        return contextMap;
    }
}
