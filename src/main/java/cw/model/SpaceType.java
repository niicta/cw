package cw.model;

import java.util.HashMap;
import java.util.Map;

public enum SpaceType
{
    MEETING_ROOM(0), WORK_PLACE(1);

    private int number;

    private static Map<Integer, SpaceType> map = new HashMap<Integer, SpaceType>();

    static
    {
        for (SpaceType type : SpaceType.values())
        {
            map.put(type.number, type);
        }
    }

    private SpaceType(final int number){
        this.number = number;
    }

    public static SpaceType valueOf(int number){
        return map.get(number);
    }
}
