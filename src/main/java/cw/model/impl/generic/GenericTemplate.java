package cw.model.impl.generic;

import cw.model.SpaceType;
import cw.model.Template;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GenericTemplate implements Template{

    @Id
    @GeneratedValue
    private int id;
    private SpaceType spaceType;
    private boolean fixed;
    private boolean  fullWeek;
    private int countOfPlaces;
    private double basePricePerHour;
    private String name;

    public GenericTemplate(int id, SpaceType spaceType, boolean fixed, boolean fullWeek, int countOfPlaces, double basePricePerHour, String name) {
        this.id = id;
        this.spaceType = spaceType;
        this.fixed = fixed;
        this.fullWeek = fullWeek;
        this.countOfPlaces = countOfPlaces;
        this.basePricePerHour = basePricePerHour;
        this.name = name;
    }

    public GenericTemplate(SpaceType spaceType, boolean fixed, boolean fullWeek, int countOfPlaces, double basePricePerHour, String name){
        this.spaceType = spaceType;
        this.fixed = fixed;
        this.fullWeek = fullWeek;
        this.countOfPlaces = countOfPlaces;
        this.basePricePerHour = basePricePerHour;
        this.name = name;
    }

    public GenericTemplate(){
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
    public SpaceType getSpaceType() {
        return spaceType;
    }

    @Override
    public void setSpaceType(SpaceType spaceType) {
        this.spaceType = spaceType;
    }

    @Override
    public boolean isFixed() {
        return fixed;
    }

    @Override
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    @Override
    public boolean isFullWeek() {
        return fullWeek;
    }

    @Override
    public void setFullWeek(boolean fullWeek) {
        this.fullWeek = fullWeek;
    }

    @Override
    public int getCountOfPlaces() {
        return countOfPlaces;
    }

    @Override
    public void setCountOfPlaces(int countOfPlaces) {
        this.countOfPlaces = countOfPlaces;
    }

    @Override
    public double getBasePricePerHour() {
        return basePricePerHour;
    }

    @Override
    public void setBasePricePerHour(double basePricePerHour) {
        this.basePricePerHour = basePricePerHour;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
