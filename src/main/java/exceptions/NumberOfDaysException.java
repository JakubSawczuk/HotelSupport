package exceptions;

import gui.NewAlert;

/**
 * Created by Kuba on 2018-01-29.
 */
public class NumberOfDaysException extends IndexOutOfBoundsException {

    public NumberOfDaysException(String pesel, String numberRoom, String days) {
        new NewAlert("Error", "Blad dodawania zamowienia",
                "Nie moge dodac zamowienia o danych:\n"
                        + "PESEL: " + pesel + "\n"
                        + "Numer pokoju: " + numberRoom + "\n"
                        + "Na okres: " + days + " dni" + "\n"
                        + "Powod: Liczba dni musi byc wieksza od 0");
    }
}
