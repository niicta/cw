package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.Order;
import cw.model.Template;
import cw.model.User;
import cw.model.factory.ModelFactory;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;
import java.util.ArrayList;

public class CreateOrderCommand implements Command
{
    private User user;
    private Template template;
    @EJB(beanName = "userDao")
    private DAO<User> userDao;
    @EJB(beanName = "templateDao")
    private DAO<Template> templateDao;
    @EJB(beanName = "orderDao")
    private DAO<Order> orderDAO;
    @EJB
    private ModelFactory modelFactory;

    @Override
    public boolean canExecute(ContextMap commandContext){
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.CREATE_ORDER_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext){
        fillParametersFromContext(commandContext);
        Order createdOrder = createOrder();
        saveOrder(createdOrder);
        return buildResultMap(createdOrder);
    }

    private void fillParametersFromContext(ContextMap commandContext) {
        int userId = (Integer) commandContext.getValue(ControllerCommandConstants.USER_ID_ATTRIBUTE);
        int templateId = (Integer) commandContext.getValue(ControllerCommandConstants.TEMPLATE_ID_ATTRIBUTE);
        user = userDao.findById(userId);
        template = templateDao.findById(templateId);
    }

    private Order createOrder(){
        return modelFactory.createOrder(user, new ArrayList<>(), template);
    }

    private void saveOrder(Order createdOrder){
        orderDAO.save(createdOrder);
    }

    private ContextMap buildResultMap(Order createdOrder){
        ContextMap result = new ContextMap();
        result.put(ControllerCommandConstants.ORDER_OBJECT_ATTRIBUTE, createdOrder);
        return result;
    }
}
