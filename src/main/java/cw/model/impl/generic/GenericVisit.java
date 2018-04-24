package cw.model.impl.generic;

import cw.model.Order;
import cw.model.Space;
import cw.model.Visit;

import java.util.Calendar;

public class GenericVisit implements Visit {
    private int id;
    private Order order;
    private Calendar startDate;
    private Calendar endDate;
    private Space space;
    private boolean fixed;

    public GenericVisit(int id, Order order, Calendar startDate, Calendar endDate, Space space, boolean fixed) {
        this.id = id;
        this.order = order;
        this.startDate = startDate;
        this.endDate = endDate;
        this.space = space;
        this.fixed = fixed;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public Calendar getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    @Override
    public Calendar getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    @Override
    public Space getSpace() {
        return space;
    }

    @Override
    public void setSpace(Space space) {
        this.space = space;
    }

    @Override
    public boolean isFixed() {
        return fixed;
    }

    @Override
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
