package cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public interface Space
{
    int getId();
    void setId(int id);
    SpaceType getSpaceType();
    void setSpaceType(SpaceType spaceType);
    int getCountOfSeats();
    void setCountOfSeats(int countOfSeats);
}
