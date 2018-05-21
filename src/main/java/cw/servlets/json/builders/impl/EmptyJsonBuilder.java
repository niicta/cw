package cw.servlets.json.builders.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import cw.servlets.json.builders.JsonBuilder;
import cw.utils.context.ContextMap;

public class EmptyJsonBuilder implements JsonBuilder
{
    @Override
    public boolean canBuild(ContextMap contextMap){
        return contextMap.equals(ContextMap.EMPTY_OPERATION_RESULT);
    }

    @Override
    public JsonElement buildJSONByContext(ContextMap contextMap){
        JsonObject jsonObject = new JsonObject();
        return jsonObject;
    }
}
