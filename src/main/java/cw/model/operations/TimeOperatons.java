package cw.model.operations;

import java.util.Calendar;

public class TimeOperatons {
    public static boolean theSameDateAndHour(Calendar firstDate, Calendar secondDate){
        return firstDate.get(Calendar.YEAR)==secondDate.get(Calendar.YEAR)
                && firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH)
                && firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH)
                && firstDate.get(Calendar.HOUR_OF_DAY) == secondDate.get(Calendar.HOUR_OF_DAY);
    }

    public static boolean laterThan(Calendar firstDate, Calendar secondDate) {
        if (firstDate.get(Calendar.YEAR) > secondDate.get(Calendar.YEAR)) {
            return true;
        }
        else if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR)
                && firstDate.get(Calendar.MONTH) > secondDate.get(Calendar.MONTH)) {
            return true;
        }
        else if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR)
                && firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH)
                && firstDate.get(Calendar.DAY_OF_MONTH) > secondDate.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        else if (firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR)
                && firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH)
                && firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH)
                && firstDate.get(Calendar.HOUR_OF_DAY) > secondDate.get(Calendar.HOUR_OF_DAY)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String calendarToDateString(Calendar calendar){
        return String.format("%d.%d.%d",
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    }
}
