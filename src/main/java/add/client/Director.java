package add.client;

import database.entity.Client;

/**
 * Created by Kuba on 2018-01-14.
 */
public class Director {

    private BuilderClient builderClient;

    public void setBuilderClient(BuilderClient builderClient) {
        this.builderClient = builderClient;
    }


    public Client getClient() {
        return builderClient.getClient();
    }



    public void addClientToDatabase(String thePesel, String theCompanyName, String theFirstName,
                                    String theSurName, String theNIP) {
        builderClient.newClient();
        builderClient.setPesel(thePesel);
        builderClient.setCompanyName(theCompanyName);
        builderClient.setFirstName(theFirstName);
        builderClient.setSurName(theSurName);
        builderClient.setNIP(theNIP);
    }

}
