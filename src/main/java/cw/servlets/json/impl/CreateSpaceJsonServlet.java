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

public class CreateSpaceJsonServlet extends AbstractJsonServlet {
    private static final Logger LOG = Logger.getLogger(CreateSpaceJsonServlet.class);
    static {
        LOG.debug("space json servlet is up");
    }

    private static final String SPACE_TYPE = "space-type";
    private static final String COUNT_OF_PLACES = "count-of-places";

    private int spaceType;
    private int countOfPlaces;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("create space requested");
        resp.setContentType("text/json;Charset=UTF-8");
        fillParametersFromRequest(req);
        ContextMap contextMap = buildCommandContext();
        JsonElement responseObject = buildResponse(contextMap);
        sendResponse(resp.getWriter(), responseObject);
    }

    private void fillParametersFromRequest(HttpServletRequest req) {
        spaceType = Integer.valueOf(req.getParameter(SPACE_TYPE));
        countOfPlaces = Integer.valueOf(req.getParameter(COUNT_OF_PLACES));
        LOG.debug(String.format("params: %d %d", countOfPlaces, spaceType));
    }

    private ContextMap buildCommandContext() {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE, ControllerCommandConstants.CREATE_SPACE_COMMAND_VALUE);
        contextMap.put(ControllerCommandConstants.SPACE_TYPE_ATTRIBUTE, spaceType);
        contextMap.put(ControllerCommandConstants.COUNT_OF_PLACES_ATTRIBUTE, countOfPlaces);
        return contextMap;
    }
}
