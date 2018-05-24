package cw.model.impl.generic;

import cw.model.Attribute;
import cw.model.Space;
import cw.model.SpaceType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@NamedQuery(name = "GenericSpace.findAll", query = "SELECT s FROM GenericSpace s")

public class GenericSpace implements Space{

    @Id
    @GeneratedValue
    private int id;
    private SpaceType spaceType;
    private int countOfSeats;
    @ElementCollection
    @CollectionTable(name="space_params")
    @MapKeyColumn(name = "attribute")
    @Column(name = "value")
    private Map<GenericAttribute, String> params;

    public GenericSpace(int id, SpaceType spaceType, int countOfSeats){
        this.id = id;
        this.spaceType = spaceType;
        this.countOfSeats = countOfSeats;
        this.params=  new HashMap<>();
    }

    public GenericSpace(SpaceType spaceType, int countOfSeats){
        this.spaceType = spaceType;
        this.countOfSeats = countOfSeats;
        this.params=  new HashMap<>();
    }

    public GenericSpace(){
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public SpaceType getSpaceType(){
        return spaceType;
    }

    @Override
    public void setSpaceType(SpaceType spaceType){
        this.spaceType = spaceType;
    }

    @Override
    public int getCountOfSeats(){
        return countOfSeats;
    }

    @Override
    public void setCountOfSeats(int countOfSeats){
        this.countOfSeats = countOfSeats;
    }

    @Override
    public Map<Attribute, String> getParameters(){
        Map<Attribute, String> paramsToReturn = new HashMap<>();
        for (GenericAttribute genericAttribute: params.keySet()){
            paramsToReturn.put(genericAttribute, params.get(genericAttribute));
        }
        return paramsToReturn;
    }

    @Override
    public void setParameters(Map<Attribute, String> parameters){
        this.params = new HashMap<>();
        for (Attribute genericAttribute: parameters.keySet()){
            params.put((GenericAttribute) genericAttribute, parameters.get(genericAttribute));
        }
    }

    @Override
    public String toString() {
        return String.format("space id = %d space type %s count of seats %d",
                id, spaceType.name(), countOfSeats);
    }
}
