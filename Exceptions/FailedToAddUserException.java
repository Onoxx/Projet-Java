package Exceptions;

import MVC.Model.User;

public class FailedToAddUserException extends RuntimeException {
    public FailedToAddUserException(User user) {
        super("L'utilisateur " + user.getName() + " n'a pas pu être ajouté !\n");
    }
}
