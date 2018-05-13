package cw.controller.command;

import cw.controller.command.impl.EmptyCommand;
import cw.utils.context.ContextMap;

public interface Command
{
    boolean canExecute(ContextMap commandContext);
    ContextMap execute(ContextMap commandContext);
    Command EMPTY = new EmptyCommand();
}
