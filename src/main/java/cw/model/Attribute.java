package cw.model;

public interface Attribute
{
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    AttributeType getAttributeType();
    void setAttributeType(AttributeType attributeType);
}
