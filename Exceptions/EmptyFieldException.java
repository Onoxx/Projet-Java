package Exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException() {
        super("Tous les champs doivent être remplis !\n");
    }
}
