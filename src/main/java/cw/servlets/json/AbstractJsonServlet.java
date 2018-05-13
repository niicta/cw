package cw.servlets.json;

import com.google.gson.JsonElement;
import cw.controller.Controller;
import cw.servlets.json.builders.JsonBuilderFactory;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;

public abstract class AbstractJsonServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AbstractJsonServlet.class);
    @EJB
    private Controller controller;
    @EJB
    private JsonBuilderFactory jsonBuilderFactory;

    protected JsonElement buildResponse(ContextMap contextMap) {
        LOG.debug("Building response");
        ContextMap commandResult = controller.executeCommandByContext(contextMap);
        return buildResponseJsonByResult(commandResult);
    }

    protected JsonElement buildResponseJsonByResult(ContextMap result){
        return jsonBuilderFactory.createBuilderByContext(result).buildJSONByContext(result);
    }

    protected void sendResponse(PrintWriter printWriter, JsonElement responseObject){
        printWriter.write(responseObject.toString());
        printWriter.flush();
    }
}
