package Exceptions;

public class FailedToGetComponentException extends RuntimeException {
    public FailedToGetComponentException(String componentName) {
        super("Erreur dans la récupération du " + componentName);
    }
}
