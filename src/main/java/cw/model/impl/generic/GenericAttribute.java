package cw.model.impl.generic;

import cw.model.Attribute;
import cw.model.AttributeType;

import javax.persistence.*;

@Entity
@NamedQuery(name = "GenericAttribute.findAll", query = "SELECT o FROM GenericAttribute o")
public class GenericAttribute implements Attribute
{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToOne
    private GenericAttributeType attributeType;

    @Override
    public int getId(){
        return 0;
    }

    @Override
    public void setId(int id){

    }

    @Override
    public String getName(){
        return null;
    }

    @Override
    public void setName(String name){

    }

    @Override
    public AttributeType getAttributeType(){
        return null;
    }

    @Override
    public void setAttributeType(AttributeType attributeType){

    }
}
