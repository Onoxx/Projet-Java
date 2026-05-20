package Exceptions;

public class ValueASmallerThanBException extends RuntimeException {
    public ValueASmallerThanBException(String componentA, String componentB) {
        super("La valeur de " + componentA + " doit être au moins égale à la valeur de " + componentB);
    }
}
