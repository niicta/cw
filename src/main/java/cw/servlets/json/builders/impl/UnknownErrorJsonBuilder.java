package cw.servlets.json.builders.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import cw.servlets.json.JsonConstants;
import cw.servlets.json.builders.JsonBuilder;
import cw.utils.context.ContextMap;

public class UnknownErrorJsonBuilder implements JsonBuilder {
    public UnknownErrorJsonBuilder() {
    }

    public boolean canBuild(ContextMap contextMap){
        return contextMap.equals(ContextMap.UNKNOWN_ERROR_MAP);
    }

    public JsonObject buildJSONByContext(ContextMap contextMap){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonConstants.STATUS_PROPERTY_NAME, JsonConstants.UNKNOWN_ERROR);
        return jsonObject;
    }
}
