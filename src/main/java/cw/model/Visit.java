package cw.model;

import java.util.Calendar;

public interface Visit {
    int getId();
    Order getOrder();
    Calendar getStartDate();
    void setStartDate(Calendar startDate);
    Calendar getEndDate();
    void setEndDate(Calendar endDate);
    Space getSpace();
    void setSpace(Space space);
    boolean isFixed();
    void setFixed(boolean fixed);
}
