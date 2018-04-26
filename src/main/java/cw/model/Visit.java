package cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

public interface Visit {
    int getId();
    void setId(int id);
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
