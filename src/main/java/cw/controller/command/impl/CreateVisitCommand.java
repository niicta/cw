package cw.controller.command.impl;

import cw.check.CheckerConstants;
import cw.check.impl.VisitCreationPossibilityChecker;
import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.model.Space;
import cw.model.Visit;
import cw.model.factory.ModelFactory;
import cw.model.operations.TimeOperatons;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import java.util.Calendar;
import java.util.Collection;

public class CreateVisitCommand implements Command
{
    private static final Logger LOG = Logger.getLogger(CreateVisitCommand.class);
    private Calendar visitStartDate;
    private Calendar visitEndDate;
    private Order order;
    @EJB(beanName = "orderDao")
    private DAO<Order> orderDao;
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;
    @EJB(beanName = "visitCreationPossibilityChecker")
    private VisitCreationPossibilityChecker visitCreationPossibilityChecker;
    @EJB
    private ModelFactory modelFactory;

    @Override
    public boolean canExecute(ContextMap commandContext){
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.CREATE_VISIT_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext){
        try {
            debug("CreateVisitCommand started");
            fillParametersFromContext(commandContext);
            debug(String.format("params : startDate %s endDate %s order %s"
                    , calendarToDateAndHourString(visitStartDate), calendarToDateAndHourString(visitEndDate), order));
            //TODO Убрать, когда научусь создавать потоки в контейнере
            removeAllVisitsEndedEarlierThanToday();
            ContextMap checkResult = visitCreationPossibilityChecker.check(commandContext);
            if (checkResult.getValue(CheckerConstants.CHECKER_RESULT_ATTRIBUTE)
                    .equals(CheckerConstants.CHECKER_RESULT_FAIL_VALUE)) {
                debug("can't create visit");
                return buildCheckFailedResult(checkResult);
            }
            Space availableSpace = (Space) checkResult.getValue(CheckerConstants.AVAILABLE_SPACE_ATTRIBUTE);
            debug("can create visit for space - " + availableSpace.getId());
            Visit createdVisit = createVisit(availableSpace);
            saveVisit(createdVisit);
            debug("createVisit end. created visit - " + createdVisit);
            return buildResultMap(createdVisit);
        }
        catch (Exception ex){
            LOG.debug("ex " , ex);
            throw ex;
        }
    }

    private void removeAllVisitsEndedEarlierThanToday() {
        Collection<Visit> visits =  visitDao.findAll();
        Calendar today = Calendar.getInstance();
        debug("removing old visits. today - " + calendarToDateAndHourString(today));
        for (Visit visit : visits){
            debug("current visit - " + visit);
            if (TimeOperatons.laterThan(today, visit.getEndDate())) {
                debug("remove");
                visitDao.delete(visit);
            }
        }
        debug("remove ended");
    }

    private ContextMap buildCheckFailedResult(ContextMap checkResult) {
        ContextMap errorContext = new ContextMap();
        errorContext.put(ControllerCommandConstants.ERROR_ATTRIBUTE, ControllerCommandConstants.VISIT_CREATION_ERROR_VALUE);
        errorContext.put(ControllerCommandConstants.ERROR_REASON_ATTRIBUTE, checkResult.getValue(CheckerConstants.CHECKER_FAIL_REASON));
        return errorContext;
    }

    private ContextMap buildResultMap(Visit visit){
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.VISIT_OBJECT_ATTRIBUTE, visit);
        return contextMap;
    }

    private void saveVisit(Visit createdVisit) {
        visitDao.save(createdVisit);
        Collection<Visit> updatedVisits = order.getVisits();
        updatedVisits.add(createdVisit);
        order.setVisits(updatedVisits);
        debug("order - " + order);
        orderDao.update(order);
        debug("refreshed order - " + order);
        debug("refreshed order from db - " + orderDao.findById(order.getId()));
    }

    private Visit createVisit(Space availableSpace) {
        return modelFactory.createVisit(order, visitStartDate, visitEndDate, availableSpace, order.getTemplate().isFixed());
    }

    private void fillParametersFromContext(ContextMap commandContext) {
        visitStartDate = (Calendar) commandContext.getValue(ControllerCommandConstants.VISIT_START_DATE_ATTRIBUTE);
        visitEndDate = (Calendar) commandContext.getValue(ControllerCommandConstants.VISIT_END_DATE_ATTRIBUTE);
        int orderId = (Integer) commandContext.getValue(ControllerCommandConstants.ORDER_ID_ATTRIBUTE);
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
