package cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
