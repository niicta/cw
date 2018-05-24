package cw.model.operations;

import java.util.Comparator;

public class BooleanHandler implements AttributeTypeHandler
{
    public BooleanHandler(){
    }

    @Override
    public int compare(String o1, String o2){
        if (Boolean.valueOf(o1).equals(Boolean.valueOf(o2)))
            return 0;
        return !Boolean.valueOf(o1) || Boolean.valueOf(o2) ? -1: 1;
    }
}
