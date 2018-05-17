package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.model.Space;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;
import java.util.Calendar;

public class CreateVisitCommand implements Command
{
    private Calendar visitStartDate;
    private Calendar visitEndDate;
    private Order order;
    @EJB(beanName = "orderDao")
    private DAO<Order> orderDao;
    @Override
    public boolean canExecute(ContextMap commandContext){
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.CREATE_VISIT_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext){
        fillParametersFromContext(commandContext);
        Space createdSpace = createSpace();
        saveSpace(createdSpace);
        return buildResultMap(createdSpace);
    }

    private void fillParametersFromContext(ContextMap commandContext) {
        visitStartDate = (Calendar) commandContext.getValue(ControllerCommandConstants.VISIT_START_DATE_ATTRIBUTE);
        visitEndDate = (Calendar) commandContext.getValue(ControllerCommandConstants.VISIT_END_DATE_ATTRIBUTE);
        int orderId = (Integer) commandContext.getValue(ControllerCommandConstants.ORDER_ID_ATTRIBUTE);
        order = orderDao.findById(orderId);
    }
}
