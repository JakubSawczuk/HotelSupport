package exceptions;

import database.SupportDatabase;
import database.entity.Client;
import javafx.scene.control.Alert;

import java.sql.SQLException;

/**
 * Created by Kuba on 2018-01-14.
 */
public class DuplicatePrimaryKeyException extends SQLException {

    public DuplicatePrimaryKeyException(String pesel) {
        Client client = SupportDatabase.entityManager.find(Client.class, pesel);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Blad dodawania klienta");
        alert.setHeaderText("Taki PESEL wystepuje dla: " + "\n"
                + "Imie: " + client.getFirstName() + "\n"
                + "Nazwisko: " + client.getSurName() + "\n"
                + "Nazwa firmy: " + client.getCompanyName() + "\n"
                + "NIP: " + client.getNIP());

        alert.show();
    }
}
