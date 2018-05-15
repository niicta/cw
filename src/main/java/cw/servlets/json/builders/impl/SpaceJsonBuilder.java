package cw.servlets.json.builders.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import cw.controller.command.ControllerCommandConstants;
import cw.model.Space;
import cw.model.Template;
import cw.servlets.json.builders.JsonBuilder;
import cw.utils.context.ContextMap;

public class SpaceJsonBuilder implements JsonBuilder {
    @Override
    public boolean canBuild(ContextMap contextMap) {
        return contextMap.containsAttribute(ControllerCommandConstants.SPACE_OBJECT_ATTRIBUTE);
    }

    @Override
    public JsonElement buildJSONByContext(ContextMap contextMap) {
        Space space = (Space) contextMap.getValue(ControllerCommandConstants.SPACE_OBJECT_ATTRIBUTE);
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String jsonString = mapper.writeValueAsString(space);
            JsonParser jsonParser = new JsonParser();
            return jsonParser.parse(jsonString);
        } catch (JsonProcessingException e)
        {
            return buildErrorJson();
        }
    }

    private JsonElement buildErrorJson(){
        ContextMap errorContext = ContextMap.UNKNOWN_ERROR_MAP;
        UnknownErrorJsonBuilder unknownErrorJSONBuilder = new UnknownErrorJsonBuilder();
        return unknownErrorJSONBuilder.buildJSONByContext(errorContext);
    }
}
