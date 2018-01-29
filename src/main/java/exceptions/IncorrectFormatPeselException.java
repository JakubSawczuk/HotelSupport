package exceptions;

import gui.LogInWindow;
import gui.NewAlert;

/**
 * Created by Kuba on 2018-01-29.
 */
public class IncorrectFormatPeselException extends Throwable {
    public IncorrectFormatPeselException() {
        new NewAlert("Error", LogInWindow.properties.getProperty("titleIncorrect"),
                LogInWindow.properties.getProperty("headerIncorret1") + "\n"
                        + LogInWindow.properties.getProperty("headerIncorret2"));
    }
}
