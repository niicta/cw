package cw.servlets.json.builders.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cw.controller.command.ControllerCommandConstants;
import cw.model.User;
import cw.servlets.json.JsonConstants;
import cw.servlets.json.builders.JsonBuilder;
import cw.utils.context.ContextMap;

public class UserJsonBuilder implements JsonBuilder {
    @Override
    public boolean canBuild(ContextMap contextMap) {
        return contextMap.containsAttribute(ControllerCommandConstants.USER_ATTRIBUTE);
    }

    @Override
    public JsonElement buildJSONByContext(ContextMap contextMap) {
        String userName = ((User)contextMap.getValue(ControllerCommandConstants.USER_ATTRIBUTE)).getName();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonConstants.USER_NAME_PROPERTY_NAME, userName);
        return jsonObject;
    }
}
