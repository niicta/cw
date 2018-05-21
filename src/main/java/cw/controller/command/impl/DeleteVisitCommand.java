package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.model.Visit;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;
import java.util.Collection;
import java.util.LinkedList;

public class DeleteVisitCommand implements Command
{
    @EJB(beanName = "orderDao")
    private DAO<Order> orderDao;
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;

    @Override
    public boolean canExecute(ContextMap commandContext){
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.DELETE_VISIT_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext){
        int visitToDeleteId = (Integer)commandContext.getValue(ControllerCommandConstants.VISIT_TO_DELETE_ID_ATTRIBUTE);
        deleteVisit(visitToDeleteId);
        return ContextMap.EMPTY_OPERATION_RESULT;
    }
    private void deleteVisit(int visitToDeleteId){
        Visit visitToDelete = visitDao.findById(visitToDeleteId);
        deleteVisitFromOrder(visitToDelete);
        visitDao.delete(visitToDelete);
    }

    private void deleteVisitFromOrder(Visit visitToDelete){
        Order order = visitToDelete.getOrder();
        Collection<Visit> updatedVisits = new LinkedList<>();
        for (Visit visit : order.getVisits()){
            if (visit.getId() != visitToDelete.getId()){
                updatedVisits.add(visit);
            }
        }
        order.setVisits(updatedVisits);
        orderDao.update(order);
    }
}
