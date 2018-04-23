package cw.model;

public interface Template
{
    int getId();
    SpaceType getSpaceType();
    void setSpaceType(SpaceType spaceType);
    boolean isFixed();
    void setFixed(boolean fixed);
    boolean isFullWeek();
    void setFullWeek(boolean fullWeek);
    int getCountOfPlaces();
    void setCountOfPlaces(int countOfPlaces);
    double getBasePricePerHour();
    void setBasePricePerHour(double pricePerHour);
    String getName();
    void setName(String name);
}
