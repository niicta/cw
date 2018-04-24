package cw.model.impl.generic;

import cw.model.Order;
import cw.model.Template;
import cw.model.User;
import cw.model.Visit;

import java.util.Collection;

public class GenericOrder implements Order {
    private int id;
    private User user;
    private Collection<Visit> visits;
    private Template template;

    public GenericOrder(int id, User user, Collection<Visit> visits, Template template) {
        this.id = id;
        this.user = user;
        this.visits = visits;
        this.template = template;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Collection<Visit> getVisits() {
        return visits;
    }

    @Override
    public void setVisits(Collection<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public Template getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(Template template) {
        this.template = template;
    }
}
