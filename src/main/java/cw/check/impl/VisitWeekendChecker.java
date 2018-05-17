package cw.check.impl;

import cw.check.AbstractChecker;
import cw.check.Checker;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;

@Stateless(name = "visitWeekendChecker", mappedName = "visitWeekendChecker")
public class VisitWeekendChecker extends AbstractChecker {
    private static final String reason = "Невозможно создать посещение на выходной день";

    @EJB(beanName = "orderDao")
    private DAO<Order> orderDao;
    private Calendar visitStartDate;
    private Order order;

    @Override
    public ContextMap check(ContextMap contextMap) {
        fillParamsFromContext(contextMap);
        if (!order.getTemplate().isFullWeek()){
            if (visitStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    visitStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                return buildFailResult(reason);
            }
        }
        return buildSuccessResult();
    }

    private void fillParamsFromContext(ContextMap contextMap) {
        visitStartDate = (Calendar) contextMap.getValue(ControllerCommandConstants.VISIT_START_DATE_ATTRIBUTE);
        int orderId = (Integer) contextMap.getValue(ControllerCommandConstants.ORDER_ID_ATTRIBUTE);
        order = orderDao.findById(orderId);
    }


}
