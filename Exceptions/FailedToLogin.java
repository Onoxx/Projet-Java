package Exceptions;

public class FailedToLogin extends RuntimeException {
    public FailedToLogin() {
        super("Erreur lors de la connexion !");
    }
}
