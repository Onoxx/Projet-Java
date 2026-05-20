package Exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(int quantity) {
        super("Erreur, vous avez donné une quantité de : " + quantity + "\nVeuillez réessayer !");
    }
}
