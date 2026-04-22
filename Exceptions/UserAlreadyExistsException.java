package Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Cet utilisateur existe déjà !");
    }
}
