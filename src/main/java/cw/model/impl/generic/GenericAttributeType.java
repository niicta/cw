package cw.model.impl.generic;

import cw.model.AttributeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "GenericAttributeType.findAll", query = "SELECT o FROM GenericAttributeType o")
public class GenericAttributeType implements AttributeType
{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String handlerClassName;

    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    public String getHandlerClassName(){
        return handlerClassName;
    }

    public void setHandlerClassName(String comparatorClassName){
        this.handlerClassName = comparatorClassName;
    }
}
