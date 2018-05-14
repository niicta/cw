package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.data.impl.jpa.impl.generic.GenericTemplateJpaDao;
import cw.model.SpaceType;
import cw.model.Template;
import cw.model.factory.ModelFactory;
import cw.model.impl.generic.GenericTemplate;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;

public class CreateTemplateCommand implements Command
{
    @EJB
    private ModelFactory modelFactory;
    @EJB(beanName = "templateDao")
    private DAO<Template> templateDAO;
    private boolean fixed;
    private boolean fullWeel;
    private int countOfPlaces ;
    private double basePricePerHour;
    private String name;
    private SpaceType spaceType;

    @Override
    public boolean canExecute(ContextMap commandContext){
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.CREATE_TEMPLATE_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext){
        fillParametersFromContext(commandContext);
        Template createdTemplate = createTemplate();
        saveTemplate(createdTemplate);
        return buildResultMap(createdTemplate);
    }

    private ContextMap buildResultMap(Template createdTemplate){
        ContextMap resultMap = new ContextMap();
        resultMap.put(ControllerCommandConstants.TEMPLATE_OBJECT_ATTRIBUTE, createdTemplate);
        return resultMap;
    }

    private void saveTemplate(Template createdTemplate){
        templateDAO.save( createdTemplate);
    }

    private Template createTemplate(){
        return modelFactory.createTemplate(spaceType, fixed, fullWeel, countOfPlaces, basePricePerHour, name);
    }

    private void fillParametersFromContext(ContextMap commandContext){
        int spaceTypeId = (Integer) commandContext.getValue(ControllerCommandConstants.SPACE_TYPE_ATTRIBUTE);
        fixed = (Boolean)commandContext.getValue(ControllerCommandConstants.FIXED_SPACE_ATTRIBUTE);
        fullWeel = (Boolean)commandContext.getValue(ControllerCommandConstants.FULL_WEEK_ATTRIBUTE);
        countOfPlaces = (Integer)commandContext.getValue(ControllerCommandConstants.COUNT_OF_PLACES_ATTRIBUTE);
        basePricePerHour = (Double) commandContext.getValue(ControllerCommandConstants.BASE_PRICE_PER_HOUR_ATTRIBUTE);
        name = (String)commandContext.getValue(ControllerCommandConstants.TEMPLATE_NAME_ATTRIBUTE);
        spaceType = SpaceType.valueOf(spaceTypeId);
    }


}
