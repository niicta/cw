package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.utils.context.ContextMap;

public class EmptyCommand implements Command {
    public boolean canExecute(ContextMap commandContext){
        return false;
    }

    public ContextMap execute(ContextMap commandContext){
        return ContextMap.INVALID_OPERATION_RESULT;
    }
}
