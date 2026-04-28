package Exceptions;

public class FailedToRemoveComponentException extends RuntimeException {
    public FailedToRemoveComponentException(String componenttName) {

        super("Erreur dans la suppression du " + componenttName);
    }
}
