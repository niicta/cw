package cw.model.operations;

import cw.model.Visit;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Comparator;

@Stateless(name = "visitByEndDateComparator", mappedName = "visitByEndDateComparator")
@Local(Comparator.class)
public class VisitByEndDateComparator implements Comparator<Visit>
{
    @Override
    public int compare(Visit o1, Visit o2){
        int endYearFirst = o1.getEndDate().get(Calendar.YEAR);
        int endYearSecond = o2.getEndDate().get(Calendar.YEAR);
        int endMonthFirst = o1.getEndDate().get(Calendar.MONTH);
        int endMonthSecond = o2.getEndDate().get(Calendar.MONTH);
        int endDayFirst = o1.getEndDate().get(Calendar.DAY_OF_MONTH);
        int endDaySecond = o2.getEndDate().get(Calendar.DAY_OF_MONTH);
        int endHourFirst = o1.getEndDate().get(Calendar.HOUR_OF_DAY);
        int endHourSecond = o2.getEndDate().get(Calendar.HOUR_OF_DAY);
        if (endYearSecond - endYearFirst == 0){
            if (endMonthSecond - endMonthFirst == 0){
                if (endDaySecond - endDayFirst == 0){
                    if (endHourSecond - endHourFirst == 0){
                        return 0;
                    } else {
                        return endHourSecond - endHourFirst;
                    }
                } else{
                    return endDaySecond - endDayFirst;
                }
            } else {
                return endMonthSecond - endMonthFirst;
            }
        }else {
            return endYearSecond - endYearFirst;
        }
    }
}
