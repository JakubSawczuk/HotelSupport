package search.client;

import database.SupportDatabase;
import database.entity.Client;

import java.util.List;

/**
 * Created by Kuba on 2018-01-28.
 */
public class SearchClientBO {


    List<Client> clientList;

    private List<Client> queryGetClient(String pesel) {
        return SupportDatabase.entityManager.createQuery("SELECT e FROM Client e " +
                "WHERE Pesel = ?1", database.entity.Client.class)
                .setParameter(1,pesel).getResultList();
    }


    public SearchClientBO(String pesel) {
        clientList = queryGetClient(pesel);
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
