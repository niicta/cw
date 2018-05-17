package cw.check.impl;

import cw.check.Checker;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Visit;
import cw.model.factory.ModelFactory;
import cw.model.operations.VisitByEndDateComparator;
import cw.model.operations.VisitByStartDateComparator;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class VisitTimeEnableChecker implements Checker
{
    private Calendar visitStartDate;
    private Calendar visitEndDate;
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;
    @EJB
    private VisitByStartDateComparator  visitByStartDateComparator;
    @EJB
    private VisitByEndDateComparator visitByEndDateComparator;
    @EJB
    private ModelFactory modelFactory;

    @Override
    public ContextMap check(ContextMap contextMap){
        fillParametersByContext(contextMap);
        Collection<Visit> visits = visitDao.findAll();
        Calendar leftTime = findLeftTime(visits);
        Calendar rightTime = findRightTime(visits);


        return null;
    }

    private void fillParametersByContext(ContextMap contextMap){
        visitStartDate = (Calendar) contextMap.getValue(ControllerCommandConstants.VISIT_START_DATE_ATTRIBUTE);
        visitEndDate = (Calendar) contextMap.getValue(ControllerCommandConstants.VISIT_END_DATE_ATTRIBUTE);
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
}
