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

public class DeleteVisitJsonServlet extends AbstractJsonServlet
{
    private static final Logger LOG = Logger.getLogger(DeleteVisitJsonServlet.class);
    static {
        LOG.debug("delete visit json servlet is up");
    }

    private static final String VISIT_ID = "visit-id";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;Charset=UTF-8");
        int visitId = Integer.valueOf(req.getParameter(VISIT_ID));
        LOG.debug("delete visit json is requested for visit " + visitId);
        ContextMap contextMap = buildCommandContext(visitId);
        JsonElement responseObject = buildResponse(contextMap);
        sendResponse(resp.getWriter(), responseObject);
    }

    private ContextMap buildCommandContext(int visitId) {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE, ControllerCommandConstants.DELETE_VISIT_COMMAND_VALUE);
        contextMap.put(ControllerCommandConstants.VISIT_TO_DELETE_ID_ATTRIBUTE, visitId);
        return contextMap;
    }
}
