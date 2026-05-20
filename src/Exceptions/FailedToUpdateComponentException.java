package Exceptions;

public class FailedToUpdateComponentException extends RuntimeException {
    public FailedToUpdateComponentException(String componentName) {

        super("Erreur lors de la mise à jour du " + componentName);
    }
}
