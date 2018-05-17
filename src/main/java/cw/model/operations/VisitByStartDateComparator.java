package cw.model.operations;

import cw.model.Visit;

import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Comparator;

@Stateless
public class VisitByStartDateComparator implements Comparator<Visit>
{
    @Override
    public int compare(Visit o1, Visit o2){
        int startYearFirst = o1.getStartDate().get(Calendar.YEAR);
        int startYearSecond = o2.getStartDate().get(Calendar.YEAR);
        int startMonthFirst = o1.getStartDate().get(Calendar.MONTH);
        int startMonthSecond = o2.getStartDate().get(Calendar.MONTH);
        int startDayFirst = o1.getStartDate().get(Calendar.DAY_OF_MONTH);
        int startDaySecond = o2.getStartDate().get(Calendar.DAY_OF_MONTH);
        int startHourFirst = o1.getStartDate().get(Calendar.HOUR_OF_DAY);
        int startHourSecond = o2.getStartDate().get(Calendar.HOUR_OF_DAY);
        if (startYearFirst - startYearSecond == 0){
            if (startMonthFirst - startMonthSecond == 0){
                if (startDayFirst - startDaySecond == 0){
                    if (startHourFirst - startHourSecond == 0){
                        return 0;
                    } else {
                        return startHourFirst - startHourSecond;
                    }
                } else {
                    return startDayFirst - startDaySecond;
                }
            } else {
                return startMonthFirst - startMonthSecond;
            }
        }
        else {
            return startYearFirst - startYearSecond;
        }
    }
}
