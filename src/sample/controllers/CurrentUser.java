package sample.controllers;

import sample.models.User;

public class CurrentUser {
    private static User user = new User();
    public static void setUser(User loggedUser){
        user = loggedUser;
    }

    public static User getUser() {
        return user;
    }
}
