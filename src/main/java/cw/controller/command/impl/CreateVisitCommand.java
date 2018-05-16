package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.utils.context.ContextMap;

public class CreateVisitCommand implements Command
{
    @Override
    public boolean canExecute(ContextMap commandContext){
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.CREATE_VISIT_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext){
        return null;
    }
}
