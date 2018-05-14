package cw.servlets.json.impl;

import com.google.gson.JsonElement;
import cw.controller.command.ControllerCommandConstants;
import cw.servlets.json.AbstractJsonServlet;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTemplateJsonServlet extends AbstractJsonServlet {
    private static final Logger LOG = Logger.getLogger(CreateTemplateJsonServlet.class);
    static {
        LOG.debug("template json servlet is up");
    }

    private static final String SPACE_TYPE = "space-type";
    private static final String FIXED = "fixed";
    private static final String FULL_WEEK = "full-week";
    private static final String COUNT_OF_PLACES = "count-of-places";
    private static final String BASE_PRICE_PER_HOUR = "base-price";
    private static final String NAME = "name";

    private int spaceType;
    private boolean fixed;
    private boolean fullWeek;
    private int countOfPlaces;
    private double basePrice;
    private String name;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("create template requested");
        resp.setContentType("text/json;Charset=UTF-8");
        fillParametersFromRequest(req);
        ContextMap contextMap = buildCommandContext();
        JsonElement responseObject = buildResponse(contextMap);
        sendResponse(resp.getWriter(), responseObject);
    }

    private void fillParametersFromRequest(HttpServletRequest req) {
        spaceType = Integer.valueOf(req.getParameter(SPACE_TYPE));
        fixed = Boolean.valueOf(req.getParameter(FIXED));
        fullWeek = Boolean.valueOf(req.getParameter(FULL_WEEK));
        countOfPlaces = Integer.valueOf(req.getParameter(COUNT_OF_PLACES));
        basePrice = Double.valueOf(req.getParameter(BASE_PRICE_PER_HOUR));
        name = req.getParameter(NAME);
        LOG.debug(String.format("params: %s %f %d %b %b %d", name, basePrice, countOfPlaces
        , fullWeek, fixed, spaceType));
    }

    private ContextMap buildCommandContext() {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE, ControllerCommandConstants.CREATE_TEMPLATE_COMMAND_VALUE);
        contextMap.put(ControllerCommandConstants.TEMPLATE_NAME_ATTRIBUTE, name);
        contextMap.put(ControllerCommandConstants.SPACE_TYPE_ATTRIBUTE, spaceType);
        contextMap.put(ControllerCommandConstants.FIXED_SPACE_ATTRIBUTE, fixed);
        contextMap.put(ControllerCommandConstants.FULL_WEEK_ATTRIBUTE, fullWeek);
        contextMap.put(ControllerCommandConstants.COUNT_OF_PLACES_ATTRIBUTE, countOfPlaces);
        contextMap.put(ControllerCommandConstants.BASE_PRICE_PER_HOUR_ATTRIBUTE, basePrice);
        return contextMap;
    }
}
