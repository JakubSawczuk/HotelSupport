package exceptions;

import database.SupportDatabase;
import database.entity.Client;
import gui.LogInWindow;
import gui.NewAlert;

import java.sql.SQLException;

/**
 * Created by Kuba on 2018-01-14.
 */
public class DuplicatePrimaryKeyException extends SQLException {

    public DuplicatePrimaryKeyException(String pesel) {
        Client client = SupportDatabase.entityManager.find(Client.class, pesel);
        String headerText = LogInWindow.properties.getProperty("sthDuplicate") + "\n"
                + LogInWindow.properties.getProperty("firstname") + client.getFirstName() + "\n"
                + LogInWindow.properties.getProperty("surname") + client.getSurName() + "\n"
                + LogInWindow.properties.getProperty("companyName") + client.getCompanyName() + "\n"
                + "NIP: " + client.getNIP();
        new NewAlert("Error", LogInWindow.properties.getProperty("titleDuplicate"), headerText);
    }
}
