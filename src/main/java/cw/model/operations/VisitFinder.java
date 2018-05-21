package cw.model.operations;

import cw.data.DAO;
import cw.model.Space;
import cw.model.Visit;
import cw.model.factory.ModelFactory;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class VisitFinder {
    private static final Logger LOG = Logger.getLogger(VisitFinder.class);

    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;
    @EJB
    private ModelFactory modelFactory;

    public Collection<Visit> findForSpace(Space space){
        return findForSpace(space, visitDao.findAll());
    }

    public Collection<Visit> findSortedForSpace(Space space, Comparator<Visit> visitComparator){
        return findSortedForSpace(space, visitDao.findAll(), visitComparator);
    }

    public List<Visit> findSortedForSpace(Space space, Collection<Visit> visits, Comparator<Visit> visitComparator){
        LinkedList<Visit> foundVisits = new LinkedList<>();
        foundVisits.addAll(findForSpace(space, visits));
        foundVisits.sort(visitComparator);
        return foundVisits;
    }

    public Collection<Visit> findForSpace(Space space, Collection<Visit> visits) {
        debug("findForSpace started, \nspace - " + space + "\nvisits " + visits);
        LinkedList<Visit> visitsForSpace = new LinkedList<>();
        for (Visit visit : visits){
            debug("current visit + " + visit);
            if (visit.getSpace().getId() == space.getId()){
                visitsForSpace.add(visit);
            }
        }
        return visitsForSpace;
    }

    public Visit findFreePeriod(Calendar leftDate, Calendar rightDate, Collection<Visit> sortedVisitsCollection){
        try {
            debug("find free period started for - " + sortedVisitsCollection);
            debug("current left date - " + calendarToDateAndHourString(leftDate));
            debug("current right date - " + calendarToDateAndHourString(rightDate));
            Visit freePeriod = modelFactory.createVisit(null, leftDate, rightDate, null, false);
            if (TimeOperatons.theSameDateAndHour(leftDate, rightDate)) {
                return freePeriod;
            }
            for (Visit visit : sortedVisitsCollection) {
                debug("currentVisit - " + visit);
                if (TimeOperatons.laterThan(visit.getStartDate(), freePeriod.getStartDate())) {
                    freePeriod.setEndDate(visit.getStartDate());
                    debug("visit starts later than free period, return free period - " + freePeriod);
                    return freePeriod;
                } else {
                    freePeriod.setStartDate(visit.getEndDate());
                }
            }
            debug("return free period - " + freePeriod);
            return freePeriod;
        }
        catch (Exception e){
            LOG.debug("Exception ", e);
            throw e;
        }
    }

    private void debug(String s){
        LOG.debug(s);
    }

    private String calendarToDateAndHourString(Calendar calendar){
        return String.format("%d.%d.%d , %d hours",
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }
}
