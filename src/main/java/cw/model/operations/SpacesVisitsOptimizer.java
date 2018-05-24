package cw.model.operations;

import cw.data.DAO;
import cw.model.Attribute;
import cw.model.Space;
import cw.model.SpaceType;
import cw.model.Visit;
import cw.model.factory.ModelFactory;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class SpacesVisitsOptimizer {
    private static final Logger LOG = Logger.getLogger(SpacesVisitsOptimizer.class);
    private Calendar leftDate;
    private Calendar rightDate;
    private Calendar currentLeftDate;
    private Calendar currentRightDate;
    private List<Visit> nonCheckedVisits;
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;

    @EJB(beanName = "visitByStartDateComparator")
    private Comparator<Visit> visitByStartDateComparator;
    @EJB
    private ModelFactory modelFactory;
    @EJB
    private VisitFinder visitFinder;

    public Calendar getLeftDate() {
        return leftDate;
    }

    public void setLeftDate(Calendar leftDate) {
        this.leftDate = leftDate;
    }

    public Calendar getRightDate() {
        return rightDate;
    }

    public void setRightDate(Calendar rightDate) {
        this.rightDate = rightDate;
    }

    public Collection<Visit> optimize(Collection<Space> spaces, Collection<Visit> visits){
        debug("optimize starts for\n visits - " + visits + "\nspaces - " + spaces);
        nonCheckedVisits = new LinkedList<>();
        nonCheckedVisits.addAll(visits);
        nonCheckedVisits.sort(visitByStartDateComparator);
        debug("nonCheckedVisits - " + nonCheckedVisits);
        for (Space space : spaces){
            currentLeftDate = leftDate;
            currentRightDate = rightDate;
            List<Visit> sortedVisitsForSpace = collectSortedVisitsForSpace(space, nonCheckedVisits);
            nonCheckedVisits.removeAll(sortedVisitsForSpace);
            Visit freePeriodOfTime = findFreePeriod(currentLeftDate, sortedVisitsForSpace);
            currentLeftDate = freePeriodOfTime.getStartDate();
            debug("Current Space - " + space);
            debug("left and right dates - " + calendarToDateAndHourString(currentLeftDate) + " " + calendarToDateAndHourString(currentRightDate));
            debug("Sorted Visits for space - " + sortedVisitsForSpace);
            debug("nonCheckedVisits - " + nonCheckedVisits);
            debug("found free period of time - " + freePeriodOfTime);
            if (space.getSpaceType().equals(SpaceType.WORK_PLACE))
                optimizeForWorkPlace(space, freePeriodOfTime, sortedVisitsForSpace);
            else {
                optimizeForRoom(space, freePeriodOfTime, sortedVisitsForSpace);
            }
        }
        updateVisits(visits);
        return visits;
    }

    private void updateVisits(Collection<Visit> visits) {
        for (Visit visit: visits){
            visitDao.update(visit);
        }
    }

    private void optimizeForWorkPlace(Space space, Visit freePeriodOfTime, List<Visit> sortedVisitsForSpace){
        debug("optimize for work place started");
        while (!theSameDateAndHour(freePeriodOfTime.getStartDate(), rightDate)){
            debug("space - " + space);
            debug("sortedVisitsForSpace - " + sortedVisitsForSpace);
            debug("free period of time : " + freePeriodOfTime);
            List<Visit> possibleVisits = new LinkedList<>();
            for (int i = 0; i < nonCheckedVisits.size(); i++){
                Visit visit = nonCheckedVisits.get(i);
                debug("current visit - " + visit);
                if (visit.getOrder().getTemplate().getSpaceType().equals(space.getSpaceType())
                        && !visit.isFixed()
                        && canAccommodate(freePeriodOfTime, visit)){
                    if (canRoomSatisfyVisit(space, visit)){
                        debug("can bind for place - " + true);
                        possibleVisits.add(visit);
                    }
                }
            }
            debug("possible visits for space - " + possibleVisits);
            if (possibleVisits.size() > 0) {
                Visit visit = selectBestVisitForSpace(space, possibleVisits);
                debug("best visit for space - " + visit);
                if (visit != null) {
                    bindVisitAndSpace(visit, space, sortedVisitsForSpace);
                    freePeriodOfTime.setStartDate(visit.getEndDate());
                }
            }
            currentLeftDate = freePeriodOfTime.getEndDate();
            freePeriodOfTime = findFreePeriod(currentLeftDate, sortedVisitsForSpace);
            currentLeftDate = freePeriodOfTime.getStartDate();
        }
        debug("optimize for work place ended");
    }

    private void bindVisitAndSpace(Visit visit, Space space, List<Visit> sortedVisitsForSpace){
        debug("bindVisitAndSpace started for\n visit - " + visit + "\nspace - " + space);
        sortedVisitsForSpace.add(visit);
        sortedVisitsForSpace.sort(visitByStartDateComparator);
        nonCheckedVisits.remove(visit);
        visit.setSpace(space);
        debug("bindVisitAndSpace ended,\n visit - " + visit);
    }

    private void optimizeForRoom(Space space, Visit freePeriodOfTime, List<Visit> sortedVisitsForSpace){
        debug("optimize for work place started");
        while (!theSameDateAndHour(freePeriodOfTime.getStartDate(), rightDate)){
            debug("space - " + space);
            debug("sortedVisitsForSpace - " + sortedVisitsForSpace);
            debug("free period of time : " + freePeriodOfTime);
            List<Visit> possibleVisits = new LinkedList<>();
            for (int i = 0; i < nonCheckedVisits.size(); i++){
                Visit visit = nonCheckedVisits.get(i);
                debug("current visit - " + visit);
                if (visit.getOrder().getTemplate().getSpaceType().equals(space.getSpaceType())
                        && !visit.isFixed()
                        && canAccommodate(freePeriodOfTime, visit)){
                    if (canRoomSatisfyVisit(space, visit)){
                        debug("can bind for place - " + true);
                        possibleVisits.add(visit);
                    }
                }
            }
            debug("possible visits for space - " + possibleVisits);
            if (possibleVisits.size() > 0) {
                Visit visit = selectBestVisitForSpace(space, possibleVisits);
                debug("best visit for space - " + visit);
                if (visit != null) {
                    bindVisitAndSpace(visit, space, sortedVisitsForSpace);
                    freePeriodOfTime.setStartDate(visit.getEndDate());
                }
            }
            currentLeftDate = freePeriodOfTime.getEndDate();
            freePeriodOfTime = findFreePeriod(currentLeftDate, sortedVisitsForSpace);
            currentLeftDate = freePeriodOfTime.getStartDate();
        }
        debug("optimize for work place ended");
    }

    private Visit selectBestVisitForSpace(Space space, List<Visit> possibleVisits){
        debug("selectBestVisitForSpace started");
        debug("possibleVisits " + possibleVisits);
        debug("room " + space);
        Visit previousVisit = possibleVisits.get(0);
        int delta = space.getCountOfSeats() - previousVisit.getOrder().getTemplate().getCountOfPlaces();
        debug("previousVisit - " + previousVisit);
        debug("previous delta - " + delta);
        for (int i = 1; i < possibleVisits.size(); i++){
            Visit nextVisit = possibleVisits.get(i);
            debug("next Visit - " + nextVisit);
            if (startsLaterThan(nextVisit, previousVisit)){
                debug("selectBestVisitForSpace ended, return - " + previousVisit);
                return previousVisit;
            }
            else if (delta > space.getCountOfSeats() - nextVisit.getOrder().getTemplate().getCountOfPlaces()
                    && space.getCountOfSeats() < nextVisit.getSpace().getCountOfSeats()){
                previousVisit = nextVisit;
                delta = space.getCountOfSeats() - nextVisit.getOrder().getTemplate().getCountOfPlaces();
            }
        }
        if (space.getCountOfSeats() < previousVisit.getSpace().getCountOfSeats()) {
            debug("selectBestVisitForSpace ended, return - " + previousVisit);
            return previousVisit;
        }
        debug("selectBestVisitForSpace ended, return - " + null);
        return null;
    }

    private boolean canRoomSatisfyVisit(Space space, Visit visit) {
        boolean canSatisfy =  space.getSpaceType().equals(SpaceType.WORK_PLACE) ||
                visit.getOrder().getTemplate().getCountOfPlaces() <= space.getCountOfSeats();
        if (!canSatisfy){
            return false;
        }
        for (Attribute attribute: visit.getPreferences().keySet()){
            if (!space.getParameters().containsKey(attribute))
            {
                return false;
            }
            try
            {
                AttributeTypeHandler typeHandler = (AttributeTypeHandler) Class.forName(attribute.getAttributeType().getHandlerClassName()).newInstance();

            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private Visit findFreePeriod(Calendar currentLeftDate, Collection<Visit> sortedVisitsForSpace) {
        return visitFinder.findFreePeriod(currentLeftDate, rightDate, sortedVisitsForSpace);
    }

    private List<Visit> collectSortedVisitsForSpace(Space space, Collection<Visit> visits) {
        debug("collect sorted visits for space started. Space - " + space);
        LinkedList<Visit> visitsForSpace = new LinkedList<>();
        visitsForSpace.addAll(visitFinder.findSortedForSpace(space, visits, visitByStartDateComparator));
        debug("collect sorted visits for space ended. Return - " + visitsForSpace);
        return visitsForSpace;
    }

    private boolean startsLaterThan(Visit firstVisit, Visit secondVisit) {
        debug("later than started, left " + calendarToDateAndHourString(firstVisit.getStartDate()) + " right " + calendarToDateAndHourString(secondVisit.getStartDate()));
        return TimeOperatons.laterThan(firstVisit.getStartDate(), secondVisit.getStartDate());
    }

    private boolean canAccommodate(Visit firstVisit, Visit secondVisit) {
        Calendar firstStartDate = firstVisit.getStartDate();
        Calendar secondStartDate = secondVisit.getStartDate();
        Calendar firstEndDate = firstVisit.getEndDate();
        Calendar secondEndDate = secondVisit.getEndDate();
        return (laterThan(firstEndDate, secondEndDate) || theSameDateAndHour(firstEndDate, secondEndDate))
                && (laterThan(secondStartDate, firstStartDate) || theSameDateAndHour(secondStartDate, firstStartDate));
    }

    private boolean laterThan(Calendar firstDate, Calendar secondDate){
        return TimeOperatons.laterThan(firstDate, secondDate);
    }

    private boolean theSameDateAndHour(Calendar firstDate, Calendar secondDate) {
        debug("the same date and hour started for " + calendarToDateAndHourString(firstDate) + " and " + calendarToDateAndHourString(secondDate));
        debug("return " + TimeOperatons.theSameDateAndHour(firstDate, secondDate));
        return TimeOperatons.theSameDateAndHour(firstDate, secondDate);
    }

    private void debug(String s){
        LOG.debug(s);
    }

    private String calendarToDateAndHourString(Calendar calendar){
        return String.format("%d.%d.%d , %d hours",
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }
}
