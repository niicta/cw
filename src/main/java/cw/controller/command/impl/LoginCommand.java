package cw.controller.command.impl;

import cw.controller.command.Command;
import cw.controller.command.ControllerCommandConstants;
import cw.data.DAO;
import cw.model.User;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.Collection;

public class LoginCommand implements Command {
    @Inject
    private User currentUser;
    @EJB
    private DAO<User> userDAO;

    @Override
    public boolean canExecute(ContextMap commandContext) {
        return commandContext.getValue(ControllerCommandConstants.COMMAND_TYPE_ATTRIBUTE)
                .equals(ControllerCommandConstants.LOGIN_COMMAND_VALUE);
    }

    @Override
    public ContextMap execute(ContextMap commandContext) {
        Collection<User> users = userDAO.findAll();
        String password = (String) commandContext.getValue(ControllerCommandConstants.USER_PASSWORD_ATTRIBUTE);
        String login = (String) commandContext.getValue(ControllerCommandConstants.USER_LOGIN_ATTRIBUTE);
        for (User user : users){
            if (user.getPassword().equals(password) && (user.getEmail().equals(login) || user.getPhone().equals(login))){
                updateCurrentUser(user);
                return buildSuccessResult();
            }
        }
        return buildFailResult();
    }

    private void updateCurrentUser(User user) {
        currentUser.setId(user.getId());
        currentUser.setEmail(user.getEmail());
        currentUser.setName(user.getName());
        currentUser.setPassword(user.getPassword());
        currentUser.setPhone(user.getPhone());
        currentUser.setUserRole(user.getUserRole());
    }

    private ContextMap buildFailResult() {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.LOGIN_RESULT_ATTRIBUTE, ControllerCommandConstants.LOGIN_RESULT_FAIL);
        return contextMap;
    }

    private ContextMap buildSuccessResult() {
        ContextMap contextMap = new ContextMap();
        contextMap.put(ControllerCommandConstants.LOGIN_RESULT_ATTRIBUTE, ControllerCommandConstants.LOGIN_RESULT_SUCCESS);
        return contextMap;
    }
}
