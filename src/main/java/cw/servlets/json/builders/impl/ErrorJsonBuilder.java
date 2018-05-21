package cw.servlets.json.builders.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import cw.controller.command.ControllerCommandConstants;
import cw.servlets.json.JsonConstants;
import cw.servlets.json.builders.JsonBuilder;
import cw.utils.context.ContextMap;

public class ErrorJsonBuilder implements JsonBuilder {
    @Override
    public boolean canBuild(ContextMap contextMap) {
        return contextMap.containsAttribute(ControllerCommandConstants.ERROR_ATTRIBUTE);
    }

    @Override
    public JsonElement buildJSONByContext(ContextMap contextMap) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonConstants.ERROR_NAME_PROPERTY_NAME, (String) contextMap.getValue(ControllerCommandConstants.ERROR_ATTRIBUTE));
        jsonObject.addProperty(JsonConstants.ERROR_REASON_PROPERTY_NAME, (String) contextMap.getValue(ControllerCommandConstants.ERROR_REASON_ATTRIBUTE));
        return jsonObject;
    }
}
