package cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Map;


public interface Space
{
    int getId();
    void setId(int id);
    SpaceType getSpaceType();
    void setSpaceType(SpaceType spaceType);
    int getCountOfSeats();
    void setCountOfSeats(int countOfSeats);
    Map<Attribute, String> getParameters();
    void setParameters(Map<Attribute, String> parameters);
}
