package Exceptions;

public class FailedToAddProcessorException extends RuntimeException {
  public FailedToAddProcessorException() {
    super("Erreur dans l'ajout du processeur");
  }
}
