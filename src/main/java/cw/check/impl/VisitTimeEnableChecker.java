package cw.check.impl;

import cw.check.AbstractChecker;
import cw.check.Checker;
import cw.check.CheckerConstants;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.model.Space;
import cw.model.SpaceType;
import cw.model.Visit;
import cw.model.factory.ModelFactory;
import cw.model.operations.*;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

@Stateless(name = "visitTimeEnableChecker", mappedName = "visitTimeEnableChecker")
@Local(cw.check.Checker.class)
public class VisitTimeEnableChecker extends AbstractChecker
{
    private static final Logger LOG = Logger.getLogger(VisitTimeEnableChecker.class);
    private static final String NO_AVAILABLE_SPACE_REASON = "Не удается найти свободное место для заданного времени";

    private Calendar visitStartDate;
    private Calendar visitEndDate;
    private SpaceType requredSpaceType;
    private Calendar leftTime;
    private Calendar rightTime;
    private Order order;
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;
    @EJB(beanName = "spaceDao")
    private DAO<Space> spaceDao;
    @EJB(beanName = "visitByStartDateComparator")
    private Comparator<Visit>  visitByStartDateComparator;
    @EJB(beanName = "visitByEndDateComparator")
    private Comparator<Visit> visitByEndDateComparator;
    @EJB(beanName = "spaceByFixedTemplatesComparator")
    private Comparator<Space> spaceByFixedTemplatesComparator;
    @EJB
    private SpacesVisitsOptimizer spacesVisitsOptimizer;
    @EJB
    private ModelFactory modelFactory;
    @EJB
    private VisitSpaceResolver visitSpaceResolver;
    @EJB(beanName = "orderDao")
    private DAO<Order> orderDao;

    @Override
    public ContextMap check(ContextMap contextMap){
        debug("visittimeenablechecker started");
        fillParametersByContext(contextMap);
        Collection<Visit> visits = findAllVisits();
        rightTime = findRightTime(visits);
        leftTime = findLeftTime(visits);
        debug("right time - " + calendarToDateAndHourString(rightTime));
        debug("left time - " + calendarToDateAndHourString(leftTime));
        Collection<Space> spaces = getSortedSpaces();
        debug("sortedSpaces - " + spaces);
        Collection<Visit> optimizedVisits = optimizeVisits(visits, spaces);
        debug(" optimized visits - " + optimizedVisits);
        Visit newVisit = modelFactory.createVisit(order, visitStartDate, visitEndDate, null, false);
        visitSpaceResolver.setRightDate(rightTime);
        visitSpaceResolver.setLeftDate(leftTime);
        Space availableSpace = visitSpaceResolver.findSpaceByCurrentVisitsForNewVisit(spaces, optimizedVisits, newVisit);
        if (availableSpace == null){
            debug("no available space");
            return buildFailResult(NO_AVAILABLE_SPACE_REASON);
        }
        else{
            debug("found available space - " + availableSpace);
            ContextMap resultMap = buildSuccessResult();
            resultMap.put(CheckerConstants.AVAILABLE_SPACE_ATTRIBUTE, availableSpace);
            return resultMap;
        }
    }

    private Collection<Visit> findAllVisits() {
        Collection<Visit> visits = new LinkedList<>();
        for (Order order : orderDao.findAll()){
            visits.addAll(order.getVisits());
        }
        return visits;
    }

    private Collection<Visit> optimizeVisits(Collection<Visit> visits, Collection<Space> spaces) {
        spacesVisitsOptimizer.setLeftDate(leftTime);
        spacesVisitsOptimizer.setRightDate(rightTime);
        return spacesVisitsOptimizer.optimize(spaces, visits);
    }

    private void fillParametersByContext(ContextMap contextMap){
        visitStartDate = (Calendar) contextMap.getValue(ControllerCommandConstants.VISIT_START_DATE_ATTRIBUTE);
        visitEndDate = (Calendar) contextMap.getValue(ControllerCommandConstants.VISIT_END_DATE_ATTRIBUTE);
        int orderId = (Integer) contextMap.getValue(ControllerCommandConstants.ORDER_ID_ATTRIBUTE);
        order = orderDao.findById(orderId);
    }

    private Calendar findLeftTime(Collection<Visit> visits){
        LinkedList<Visit> visitLinkedList = new LinkedList<>(visits);
        visitLinkedList.add(modelFactory.createVisit(null, visitStartDate, null, null, false));
        visitLinkedList.sort(visitByStartDateComparator);
        return visitLinkedList.iterator().next().getStartDate();
    }

    private Calendar findRightTime(Collection<Visit> visits){
        LinkedList<Visit> visitLinkedList = new LinkedList<>(visits);
        visitLinkedList.add(modelFactory.createVisit(null, null, visitEndDate, null, false));
        visitLinkedList.sort(visitByEndDateComparator);
        return visitLinkedList.iterator().next().getEndDate();
    }

    private Collection<Space> getSortedSpaces() {
        LinkedList<Space> spaces = new LinkedList<>(spaceDao.findAll());
        spaces.sort(spaceByFixedTemplatesComparator);
        return spaces;
    }

    private void debug(String s){
        LOG.debug(s);
    }

    private String calendarToDateAndHourString(Calendar calendar){
        return String.format("%d.%d.%d , %d hours",
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }
}
