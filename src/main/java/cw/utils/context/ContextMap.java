package cw.utils.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ContextMap {
    public static final ContextMap EMPTY_OPERATION_RESULT = new ContextMap(true);
    public static final ContextMap INVALID_OPERATION_RESULT = new ContextMap(true);
    public static final ContextMap UNKNOWN_ERROR_MAP = new ContextMap(true);
    private Map<String, Object> contextMap;

    public ContextMap(){
        contextMap = new HashMap<String, Object>();
    }

    private ContextMap(boolean empty){
        contextMap = Collections.emptyMap();
    }

    public boolean containsAttribute(String attribute){
        return contextMap.containsKey(attribute);
    }

    public Object getValue(String attribute){
        return contextMap.get(attribute);
    }

    public void put(String attribute, Object value){
        contextMap.put(attribute, value);
    }

    public Set<String> getAttributes(){
        return contextMap.keySet();
    }
}
