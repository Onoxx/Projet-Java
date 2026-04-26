package Exceptions;

public class AllComponentsException extends RuntimeException {
    public AllComponentsException(String componentName) {
        super("Erreur lors de la récupération des " + componentName);
    }
}
