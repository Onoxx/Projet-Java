package Exceptions;

public class IncompatibleComponentException extends RuntimeException {
    public IncompatibleComponentException(String incomatibleComponent, String component1, String component2) {
        super("Erreur, le/la " + incomatibleComponent + " du/de la " + component1
        + " n'est pas compatible avec celui/celle du/de la " + component2);
    }
}
