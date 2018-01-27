package exceptions;

/**
 * Created by Kuba on 2018-01-14.
 */
public class PriceException extends Exception {

    public PriceException() {
        System.out.println("Cena nie moze byc ujemna!");
    }
}
