package cw.model.factory;

import cw.model.*;

import java.util.Calendar;
import java.util.Collection;

public interface ModelFactory
{
    Order createOrder(User user, Collection<Visit> visits, Template template);
    Space createSpace(SpaceType spaceType, int countOfSeats);
    Template createTemplate(SpaceType spaceType, boolean fixed, boolean fullWeek, int countOfPlaces, double basePricePerHour, String name);
    User createUser(UserRole userRole, String name, String phone, String email);
    Visit createVisit(Order order, Calendar startDate, Calendar endDate, Space space, boolean fixed);
}
