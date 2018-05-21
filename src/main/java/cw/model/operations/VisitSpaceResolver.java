package cw.model.operations;

import cw.data.DAO;
import cw.model.Space;
import cw.model.SpaceType;
import cw.model.Visit;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class VisitSpaceResolver {
    @EJB
    private VisitFinder visitFinder;
    @EJB(beanName = "visitByStartDateComparator")
    private Comparator<Visit> visitByStartDateComparator;
    private Calendar leftDate;
    private Calendar rightDate;
    private Calendar currentLeftDate;

    public Space findSpaceByCurrentVisitsForNewVisit(Collection<Space> allSpaces, Collection<Visit> visits, Visit newVisit) {
        for (Space space : allSpaces) {
            currentLeftDate = leftDate;
            if (!space.getSpaceType().equals(newVisit.getOrder().getTemplate().getSpaceType())
                    || space.getCountOfSeats() < newVisit.getOrder().getTemplate().getCountOfPlaces()) {
                continue;
            }
            Collection<Visit> sortedVisitsForSpace = collectSortedVisitsForSpace(space, visits);
            Visit freePeriodOfTime = visitFinder.findFreePeriod(currentLeftDate, rightDate, sortedVisitsForSpace);
            currentLeftDate = freePeriodOfTime.getStartDate();
            while (!TimeOperatons.theSameDateAndHour(freePeriodOfTime.getStartDate(), rightDate)) {
                if (canAccommodate(freePeriodOfTime, newVisit)) {
                    return space;
                }
                currentLeftDate = freePeriodOfTime.getEndDate();
                freePeriodOfTime = visitFinder.findFreePeriod(currentLeftDate, rightDate, sortedVisitsForSpace);
                currentLeftDate = freePeriodOfTime.getStartDate();
            }
        }
        return null;
    }

    private boolean canAccommodate(Visit firstVisit, Visit secondVisit) {
        Calendar firstStartDate = firstVisit.getStartDate();
        Calendar secondStartDate = secondVisit.getStartDate();
        Calendar firstEndDate = firstVisit.getEndDate();
        Calendar secondEndDate = secondVisit.getEndDate();
        return (TimeOperatons.laterThan(firstEndDate, secondEndDate) || TimeOperatons.theSameDateAndHour(firstEndDate, secondEndDate))
                && (TimeOperatons.laterThan(secondStartDate, firstStartDate) || TimeOperatons.theSameDateAndHour(secondStartDate, firstStartDate));
    }

    private List<Visit> collectSortedVisitsForSpace(Space space, Collection<Visit> visits) {
        return visitFinder.findSortedForSpace(space, visits, visitByStartDateComparator);
    }

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
}
