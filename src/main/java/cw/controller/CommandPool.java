package cw.controller;

import cw.controller.command.Command;
import cw.controller.command.impl.LoginCommand;
import cw.utils.context.ContextMap;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.Collection;

public class CommandPool
{
    public CommandPool(){
    }

    private Collection<Command> commands;

    @PostConstruct
    private void init(){
        commands = new ArrayList<Command>();
        commands.add(CDI.current().select(LoginCommand.class).get());
    }

    public Command selectCommand(ContextMap commandContext){
        for (Command command : commands){
            if (command.canExecute(commandContext)){
                return command;
            }
        }
        return Command.EMPTY;
    }
}
