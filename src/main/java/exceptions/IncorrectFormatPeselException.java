package exceptions;

import gui.NewAlert;

/**
 * Created by Kuba on 2018-01-29.
 */
public class IncorrectFormatPeselException extends Throwable {
    public IncorrectFormatPeselException() {
        new NewAlert("Error", "Wpisany pesel jest niepoprawny",
                "Sprawdz poprawnosc wpisanego peselu \n"
                        + "Poprawny pesel powinien miec dlugosc 11 cyfr: 0-9");
    }
}
