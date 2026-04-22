package Exceptions;

public class FailedToGetUsers extends RuntimeException {
    public FailedToGetUsers() {
        super("Erreur lors de la récupération des utilisateurs !");
    }
}
