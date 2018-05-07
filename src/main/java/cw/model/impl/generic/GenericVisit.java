package cw.model.impl.generic;

import cw.model.Order;
import cw.model.Space;
import cw.model.Visit;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@NamedQuery(name = "GenericVisit.findAll", query = "SELECT v FROM GenericVisit v")
public class GenericVisit implements Visit {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private GenericOrder order;
    private Calendar startDate;
    private Calendar endDate;
    @ManyToOne
    private GenericSpace space;
    private boolean fixed;

    public GenericVisit(int id, GenericOrder order, Calendar startDate, Calendar endDate, GenericSpace space, boolean fixed) {
        this.id = id;
        this.order = order;
        this.startDate = startDate;
        this.endDate = endDate;
        this.space = space;
        this.fixed = fixed;
    }

    public GenericVisit(GenericOrder order, Calendar startDate, Calendar endDate, GenericSpace space, boolean fixed){
        this.order = order;
        this.startDate = startDate;
        this.endDate = endDate;
        this.space = space;
        this.fixed = fixed;
    }

    public GenericVisit(){
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = (GenericOrder)order;
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
        this.space = (GenericSpace)space;
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
