package Exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String message) {
        super("Le champ \"" + message + "\" est vide");
    }
}
