package exceptions;

import database.SupportDatabase;
import database.entity.Client;
import gui.NewAlert;

import java.sql.SQLException;

/**
 * Created by Kuba on 2018-01-14.
 */
public class DuplicatePrimaryKeyException extends SQLException {

    public DuplicatePrimaryKeyException(String pesel) {
        Client client = SupportDatabase.entityManager.find(Client.class, pesel);
        String headerText = "Taki PESEL wystepuje dla: " + "\n"
                + "Imie: " + client.getFirstName() + "\n"
                + "Nazwisko: " + client.getSurName() + "\n"
                + "Nazwa firmy: " + client.getCompanyName() + "\n"
                + "NIP: " + client.getNIP();
        new NewAlert("Error", "Blad dodawania klienta", headerText);
    }
}
