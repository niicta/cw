package cw.model.operations;

import cw.model.Attribute;

import java.util.Comparator;

public class IntegerHandler implements AttributeTypeHandler
{
    public IntegerHandler(){
    }

    @Override
    public int compare(String o1, String o2){
        return Integer.valueOf(o1) - Integer.valueOf(o2);
    }
}
