package Exceptions;

public class FailedToAddComponentException extends RuntimeException {
  public FailedToAddComponentException(String componentName) {
    super("Erreur dans l'ajout d'un " + componentName);
  }
}
