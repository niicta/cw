package cw.model.impl.generic;

import cw.model.Space;
import cw.model.SpaceType;

import javax.persistence.*;

@Entity
@NamedQuery(name = "GenericSpace.findAll", query = "SELECT s FROM GenericSpace s")
@Table(name = "SPACES")
public class GenericSpace implements Space{

    @Id
    @GeneratedValue
    private int id;
    private SpaceType spaceType;
    private int countOfSeats;

    public GenericSpace(int id, SpaceType spaceType, int countOfSeats){
        this.id = id;
        this.spaceType = spaceType;
        this.countOfSeats = countOfSeats;
    }

    public GenericSpace(SpaceType spaceType, int countOfSeats){
        this.spaceType = spaceType;
        this.countOfSeats = countOfSeats;
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
}
