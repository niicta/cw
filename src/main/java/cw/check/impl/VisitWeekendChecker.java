package cw.check.impl;

import cw.check.AbstractChecker;
import cw.check.Checker;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;

@Stateless(name = "visitWeekendChecker", mappedName = "visitWeekendChecker")
@Local(cw.check.Checker.class)
public class VisitWeekendChecker extends AbstractChecker {
    private static final Logger LOG = Logger.getLogger(VisitWeekendChecker.class);
    private static final String reason = "Невозможно создать посещение на выходной день";

    @EJB(beanName = "orderDao")
    private DAO<Order> orderDao;
    private Calendar visitStartDate;
    private Order order;

    @Override
    public ContextMap check(ContextMap contextMap) {
        debug("visitweekendchecker started");
        fillParamsFromContext(contextMap);
        debug(String.format("params: startDate %s fixedTemplate %b",
                calendarToDateAndHourString(visitStartDate), order.getTemplate().isFixed()));
        if (!order.getTemplate().isFullWeek()){
            if (visitStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    visitStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                debug("failed");
                return buildFailResult(reason);
            }
        }
        debug("success");
        return buildSuccessResult();
    }

    private void fillParamsFromContext(ContextMap contextMap) {
        visitStartDate = (Calendar) contextMap.getValue(ControllerCommandConstants.VISIT_START_DATE_ATTRIBUTE);
        int orderId = (Integer) contextMap.getValue(ControllerCommandConstants.ORDER_ID_ATTRIBUTE);
        order = orderDao.findById(orderId);
    }

    private void debug(String s){
        LOG.debug(s);
    }

    private String calendarToDateAndHourString(Calendar calendar){
        return String.format("%d.%d.%d , %d hours",
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }
}
