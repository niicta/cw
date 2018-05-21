package cw.controller;

import cw.controller.command.Command;
import cw.controller.command.impl.*;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.Collection;

public class CommandPool
{
    private static final Logger LOG = Logger.getLogger(CommandPool.class);
    public CommandPool(){
    }

    private Collection<Command> commands;

    @PostConstruct
    private void init(){
        commands = new ArrayList<Command>();
        commands.add(CDI.current().select(LoginCommand.class).get());
        commands.add(CDI.current().select(CreateTemplateCommand.class).get());
        commands.add(CDI.current().select(CreateSpaceCommand.class).get());
        commands.add(CDI.current().select(CreateOrderCommand.class).get());
        commands.add(CDI.current().select(CreateVisitCommand.class).get());
    }

    public Command selectCommand(ContextMap commandContext){
        LOG.debug("selecting command");
        for (Command command : commands){
            LOG.debug("Current Command - " + command);
            if (command.canExecute(commandContext)){
                LOG.debug("return command " + command);
                return command;
            }
        }
        return Command.EMPTY;
    }
}
