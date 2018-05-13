package cw.servlets.json.builders;

import com.google.gson.JsonElement;
import cw.utils.context.ContextMap;

public interface JsonBuilder {
    boolean canBuild(ContextMap contextMap);
    JsonElement buildJSONByContext(ContextMap contextMap);
}
