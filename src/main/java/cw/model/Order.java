package cw.model;

import java.util.Collection;

public interface Order {
    int getId();
    User getUser();
    Collection<Visit> getVisits();
    void setVisits(Collection<Visit> visits);
    Template getTemplate();
    void setTemplate(Template template);
}
