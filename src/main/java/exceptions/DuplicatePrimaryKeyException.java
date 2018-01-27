package exceptions;

import database.SupportDatabase;
import database.entity.Client;

import java.sql.SQLException;

/**
 * Created by Kuba on 2018-01-14.
 */
public class DuplicatePrimaryKeyException extends SQLException {

    public DuplicatePrimaryKeyException(String pesel) {
        System.out.println("Taki PESEL juz wystepuje dla: ");
        Client client = SupportDatabase.entityManager.find(Client.class, pesel);
        client.show();
    }
}
