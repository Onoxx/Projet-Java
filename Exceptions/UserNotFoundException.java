package Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Utilisateur non trouvé !");
    }
}
