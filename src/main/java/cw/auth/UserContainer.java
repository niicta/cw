package cw.auth;

import cw.model.User;

import javax.enterprise.inject.spi.CDI;

public class UserContainer {
    private UserContainer(){
        currentUser = CDI.current().select(User.class).get();
    }

    private static UserContainer userContainer = new UserContainer();
    private User currentUser;

    public static UserContainer getUserContainer(){
        return userContainer;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
