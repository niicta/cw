package cw.servlets.json.builders.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import cw.controller.command.ControllerCommandConstants;
import cw.servlets.json.JsonConstants;
import cw.servlets.json.builders.JsonBuilder;
import cw.utils.context.ContextMap;

public class LoginResultJsonBuilder implements JsonBuilder {
    @Override
    public boolean canBuild(ContextMap contextMap) {
        return contextMap.containsAttribute(ControllerCommandConstants.LOGIN_RESULT_ATTRIBUTE);
    }

    @Override
    public JsonElement buildJSONByContext(ContextMap contextMap) {
        String jsonString = String.format("{%s : %s}", JsonConstants.LOGIN_RESULT_PROPERTY_NAME, contextMap.getValue(ControllerCommandConstants.LOGIN_RESULT_ATTRIBUTE));
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(jsonString);
    }
}
