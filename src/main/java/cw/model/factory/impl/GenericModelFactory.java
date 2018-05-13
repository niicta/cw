package cw.model.factory.impl;

import cw.model.*;
import cw.model.factory.ModelFactory;
import cw.model.impl.generic.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.*;

@Stateless
public class GenericModelFactory implements ModelFactory
{
    @Override
    public Order createOrder(User user, Collection<Visit> visits, Template template){
        Collection<GenericVisit> genericVisits = new ArrayList<GenericVisit>();
        for (Visit visit : visits){
            genericVisits.add((GenericVisit)visit);
        }
        return new GenericOrder((GenericUser)user, genericVisits, (GenericTemplate) template);
    }

    @Override
    public Space createSpace(SpaceType spaceType, int countOfSeats){
        return new GenericSpace(spaceType, countOfSeats);
    }

    @Override
    public Template createTemplate(SpaceType spaceType, boolean fixed, boolean fullWeek, int countOfPlaces, double basePricePerHour, String name){
        return new GenericTemplate(spaceType, fixed, fullWeek, countOfPlaces, basePricePerHour, name);
    }

    @Override
    public User createUser(UserRole userRole, String name, String phone, String email, String password){
        return new GenericUser(userRole, name, phone, email, password);
    }

    @Override
    public Visit createVisit(Order order, Calendar startDate, Calendar endDate, Space space, boolean fixed){
        return new GenericVisit((GenericOrder) order, startDate, endDate, (GenericSpace) space, fixed);
    }
}
