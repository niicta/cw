package cw.servlets.json.builders;

import cw.servlets.json.builders.impl.*;
import cw.utils.context.ContextMap;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.Collection;

public class JsonBuilderPool {
    public JsonBuilderPool(){

    }

    private Collection<JsonBuilder> jsonBuilders;
    private JsonBuilder unknownErrorBuilder;

    @PostConstruct
    private void init(){
        jsonBuilders = new ArrayList<JsonBuilder>();
        jsonBuilders.add(CDI.current().select(LoginResultJsonBuilder.class).get());
        jsonBuilders.add(CDI.current().select(UserJsonBuilder.class).get());
        jsonBuilders.add(CDI.current().select(SpaceJsonBuilder.class).get());
        jsonBuilders.add(CDI.current().select(TemplateJsonBuider.class).get());
        jsonBuilders.add(CDI.current().select(VisitJsonBuilder.class).get());
        jsonBuilders.add(CDI.current().select(OrderJsonBuilder.class).get());
        jsonBuilders.add(CDI.current().select(ErrorJsonBuilder.class).get());
        jsonBuilders.add(CDI.current().select(EmptyJsonBuilder.class).get());
        jsonBuilders.add(CDI.current().select(UnknownErrorJsonBuilder.class).get());
        unknownErrorBuilder = new UnknownErrorJsonBuilder();
    }

    public JsonBuilder selectBuilderByContext(ContextMap contextMap){
        for (JsonBuilder jsonBuilder : jsonBuilders){
            if (jsonBuilder.canBuild(contextMap)){
                return jsonBuilder;
            }
        }
        return unknownErrorBuilder;
    }
}
