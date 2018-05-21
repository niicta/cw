package cw.model.impl.generic;

import cw.model.Order;
import cw.model.Template;
import cw.model.User;
import cw.model.Visit;

import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NamedQuery(name = "GenericOrder.findAll", query = "SELECT o FROM GenericOrder o")

public class GenericOrder implements Order {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private GenericUser user;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<GenericVisit> visits;
    @OneToOne
    private GenericTemplate template;

    public GenericOrder(int id, GenericUser user, Collection<GenericVisit> visits, GenericTemplate template) {
        this.id = id;
        this.user = user;
        this.visits = visits;
        this.template = template;
    }

    public GenericOrder(GenericUser user, Collection<GenericVisit> visits, GenericTemplate template){
        this.user = user;
        this.visits = visits;
        this.template = template;
    }

    public GenericOrder(){
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
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user){
        this.user = (GenericUser)user;
    }

    @Override
    public Collection<Visit> getVisits() {
        Collection<Visit> visitsToReturn = new ArrayList<Visit>();
        visitsToReturn.addAll(this.visits);
        return visitsToReturn;
    }

    @Override
    public void setVisits(Collection<Visit> visits) {
        List<GenericVisit> visitsToSet = new ArrayList<GenericVisit>();
        ArrayList<Visit> incomingVisits = new ArrayList<>();
        incomingVisits.addAll(visits);
        for (int i = 0; i < incomingVisits.size(); i++){
            visitsToSet.add((GenericVisit) incomingVisits.get(i));
        }
        this.visits = visitsToSet;
    }

    @Override
    public Template getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(Template template) {
        this.template = (GenericTemplate) template;
    }

    @Override
    public String toString() {
        return String.format("order id = %d template id = %d visits - %s", id, template.getId(), visits.toString());
    }
}
