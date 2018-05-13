package cw.controller;

import cw.servlets.json.AbstractJsonServlet;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Controller
{
    private static final Logger LOG = Logger.getLogger(Controller.class);

    public Controller(){
    }

    @Inject
    private CommandPool commandPool;

    public ContextMap executeCommandByContext(ContextMap contextMap){
        LOG.debug("Using command - " + commandPool.selectCommand(contextMap));
        return commandPool.selectCommand(contextMap).execute(contextMap);
    }
}
