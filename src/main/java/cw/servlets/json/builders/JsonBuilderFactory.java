package cw.servlets.json.builders;

import cw.utils.context.ContextMap;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class JsonBuilderFactory {
    @Inject
    private JsonBuilderPool jsonBuilderPool;

    public JsonBuilderFactory(){
    }

    public JsonBuilder createBuilderByContext(ContextMap contextMap){
        try
        {
            return jsonBuilderPool.selectBuilderByContext(contextMap).getClass().newInstance();
        } catch (InstantiationException e)
        {
            return jsonBuilderPool.selectBuilderByContext(ContextMap.UNKNOWN_ERROR_MAP);
        } catch (IllegalAccessException e)
        {
            return jsonBuilderPool.selectBuilderByContext(ContextMap.UNKNOWN_ERROR_MAP);
        }
    }
}
