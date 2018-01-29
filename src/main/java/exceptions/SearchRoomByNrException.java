package exceptions;

import gui.LogInWindow;
import gui.NewAlert;

/**
 * Created by Kuba on 2018-01-29.
 */
public class SearchRoomByNrException extends IndexOutOfBoundsException {

    public SearchRoomByNrException(String pesel, String numberRoom, String days) {
        new NewAlert("Error", LogInWindow.properties.getProperty("title"),
                LogInWindow.properties.getProperty("header") + "\n"
                        + "PESEL: " + pesel + "\n"
                        + LogInWindow.properties.getProperty("numberRomExc")+ numberRoom + "\n"
                        + LogInWindow.properties.getProperty("period") + days + "" + LogInWindow.properties.getProperty("days") + "\n"
                        + LogInWindow.properties.getProperty("reasonRoom"));

    }
}
