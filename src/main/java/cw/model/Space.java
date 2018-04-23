package cw.model;

public interface Space
{
    int getId();
    SpaceType getSpaceType();
    void setSpaceType(SpaceType spaceType);
    int getCountOfSeats();
    void setCountOfSeats(int countOfSeats);
}
