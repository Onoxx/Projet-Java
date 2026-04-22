package Exceptions;

public class StringTooLongException extends RuntimeException {
    public StringTooLongException(String field, int nbChars) {
        super("Le champ \"" + field + "\" doit comporter maximum " + nbChars + " charactères !");
    }
}
