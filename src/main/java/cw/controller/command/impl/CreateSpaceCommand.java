package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Space;
import cw.model.SpaceType;
import cw.model.factory.ModelFactory;
import cw.servlets.json.impl.CreateSpaceJsonServlet;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;

public class CreateSpaceCommand implements Command {
    private int countOfSeats;
    private SpaceType spaceType;
    @EJB
    private ModelFactory modelFactory;
    @EJB(beanName = "spaceDao")
    private DAO<Space> spaceDao;
    private static final Logger LOG = Logger.getLogger(CreateSpaceCommand.class);
    @Override
    public boolean canExecute(ContextMap commandContext) {
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.CREATE_SPACE_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext) {
        try {
            fillParametersFromContext(commandContext);
            Space createdSpace = createSpace();
            saveSpace(createdSpace);
            return buildResultMap(createdSpace);
        } catch (Exception e){
            LOG.debug("ex", e);
            throw e;
        }
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
        if (spaceType.equals(SpaceType.WORK_PLACE))
            countOfSeats = 1;
        return modelFactory.createSpace(spaceType, countOfSeats);
    }

    private void fillParametersFromContext(ContextMap commandContext) {
        LOG.debug("context " + commandContext);
        countOfSeats = (Integer) commandContext.getValue(ControllerCommandConstants.COUNT_OF_PLACES_ATTRIBUTE);
        LOG.debug("count of seats " + countOfSeats);
        int spaceTypeId = (Integer) commandContext.getValue(ControllerCommandConstants.SPACE_TYPE_ATTRIBUTE);
        LOG.debug("space type " + spaceTypeId);
        spaceType = SpaceType.valueOf(spaceTypeId);
    }


}
