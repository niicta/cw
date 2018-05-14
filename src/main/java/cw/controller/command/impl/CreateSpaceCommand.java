package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Space;
import cw.model.SpaceType;
import cw.model.factory.ModelFactory;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;

public class CreateSpaceCommand implements Command {
    private int countOfSeats;
    private SpaceType spaceType;
    @EJB
    private ModelFactory modelFactory;
    @EJB(beanName = "spaceDao")
    private DAO<Space> spaceDao;

    @Override
    public boolean canExecute(ContextMap commandContext) {
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.CREATE_SPACE_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext) {
        fillParametersFromContext(commandContext);
        Space createdSpace = createSpace();
        saveSpace(createdSpace);
        return buildResultMap(createdSpace);
    }

    private ContextMap buildResultMap(Space createdSpace) {
        ContextMap result = new ContextMap();
        result.put(ControllerCommandConstants.SPACE_OBJECT_ATTRIBUTE, createdSpace);
        return result;
    }

    private void saveSpace(Space createdSpace) {
        spaceDao.save(createdSpace);
    }

    private Space createSpace() {
        return modelFactory.createSpace(spaceType, countOfSeats);
    }

    private void fillParametersFromContext(ContextMap commandContext) {
        countOfSeats = (Integer) commandContext.getValue(ControllerCommandConstants.COUNT_OF_SEATS_ATTRIBUTE);
        int spaceTypeId = (Integer) commandContext.getValue(ControllerCommandConstants.SPACE_TYPE_ATTRIBUTE);
        spaceType = SpaceType.valueOf(spaceTypeId);
    }


}
