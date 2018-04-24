package cw.model;

import java.util.Collection;

public interface Order {
    int getId();
    void setId(int id);
    User getUser();
    void setUser(User user);
    Collection<Visit> getVisits();
    void setVisits(Collection<Visit> visits);
    Template getTemplate();
    void setTemplate(Template template);
}
