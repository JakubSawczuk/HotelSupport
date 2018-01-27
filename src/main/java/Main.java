import addclient.Builder;
import addclient.ClientAdded;
import addclient.Director;
import database.SupportDatabase;
import database.entity.Client;
import exceptions.DuplicatePrimaryKeyException;
import modificationroom.Factory;

/**
 * Created by Kuba on 2017-12-19.
 */
public class Main {

    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ClientAdded();
        Factory factory = new Factory();


        factory.addOrModifyRoom("E");
        factory.addOrModifyRoom("D");
        /*TimeAndData timeAndData = new TimeAndData();
        (new Thread(timeAndData)).start();*/

        //System.out.println("Wlasciciel: ");
        director.setBuilder(builder);
        director.addClientToDatabase("9608002555", "Kubas", "Kuba", "Sawczuk", null);
        Client client = director.getClient();
        //client.show();

        try {
            SupportDatabase.persistObject(client, "9608002555");
        } catch (DuplicatePrimaryKeyException e) {}

        System.out.println("sth");

        /*SupportDatabase.entityManager.close();
        SupportDatabase.entityManagerFactory.close();*/


    }
}
