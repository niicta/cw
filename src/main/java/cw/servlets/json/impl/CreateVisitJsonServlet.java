package cw.servlets.json.impl;

import com.google.gson.JsonElement;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.model.User;
import cw.servlets.json.AbstractJsonServlet;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class CreateVisitJsonServlet extends AbstractJsonServlet {
    private static final Logger LOG = Logger.getLogger(CreateVisitJsonServlet.class);
    static {
        LOG.debug("visit json servlet is up");
    }

    private static final String START_YEAR = "start-year";
    private static final String END_YEAR = "end-year";
    private static final String START_MONTH = "start-month";
    private static final String END_MONTH = "end-month";
    private static final String START_DAY = "start-day";
    private static final String END_DAY = "end-day";
    private static final String START_HOUR = "start-hour";
    private static final String END_HOUR = "end-hour";
    private static final String ORDER_ID = "order-id";

    private Calendar startDate;
    private Calendar endDate;
    private int orderId;
    @Inject
    private User currentUser;

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
            LOG.debug("create visit requested");
            resp.setCharacterEncoding("UTF8");
            resp.setContentType("text/json;Charset=UTF-8");
            fillParametersFromRequest(req);
            ContextMap contextMap = buildCommandContext();
            JsonElement responseObject = buildResponse(contextMap);
            sendResponse(resp.getWriter(), responseObject);
        }
        catch (Exception e) {
            LOG.debug("ex", e);
            throw e;
        }
    }

    private void fillParametersFromRequest(HttpServletRequest req) {
        int startYear = Integer.valueOf(req.getParameter(START_YEAR));
        int startMonth = Integer.valueOf(req.getParameter(START_MONTH)) - 1;
        int startDay = Integer.valueOf(req.getParameter(START_DAY));
        int startHour = Integer.valueOf(req.getParameter(START_HOUR));
        int endYear = Integer.valueOf(req.getParameter(END_YEAR));
        int endMonth = Integer.valueOf(req.getParameter(END_MONTH)) - 1;
        int endDay = Integer.valueOf(req.getParameter(END_DAY));
        int endHour = Integer.valueOf(req.getParameter(END_HOUR));
        startDate = Calendar.getInstance();
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.YEAR, startYear);
        startDate.set(Calendar.MONTH, startMonth);
        startDate.set(Calendar.DAY_OF_MONTH, startDay);
        startDate.set(Calendar.HOUR_OF_DAY, startHour);
        startDate.set(startYear, startMonth, startDay, startHour, 0);
        endDate = Calendar.getInstance();
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.YEAR, endYear);
        endDate.set(Calendar.MONTH, endMonth);
        endDate.set(Calendar.DAY_OF_MONTH, endDay);
        endDate.set(Calendar.HOUR_OF_DAY, endHour);
        endDate.set(endYear, endMonth, endDay, endHour, 0);
        orderId = Integer.valueOf(req.getParameter(ORDER_ID));
        LOG.debug(String.format("params: start %s end %s order %d", calendarToDateAndHourString(startDate),
                calendarToDateAndHourString(endDate), orderId));
    }

    private ContextMap buildCommandContext() {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE, ControllerCommandConstants.CREATE_VISIT_COMMAND_VALUE);
        contextMap.put(ControllerCommandConstants.VISIT_START_DATE_ATTRIBUTE, startDate);
        contextMap.put(ControllerCommandConstants.VISIT_END_DATE_ATTRIBUTE, endDate);
        contextMap.put(ControllerCommandConstants.ORDER_ID_ATTRIBUTE, orderId);
        return contextMap;
    }

    private String calendarToDateAndHourString(Calendar calendar){
        return String.format("%d.%d.%d , %d hours",
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }
}
