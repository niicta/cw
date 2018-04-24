package cw.model;

public interface Space
{
    int getId();
    void setId(int id);
    SpaceType getSpaceType();
    void setSpaceType(SpaceType spaceType);
    int getCountOfSeats();
    void setCountOfSeats(int countOfSeats);
}
