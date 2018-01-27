package addclient;

import database.entity.Client;

/**
 * Created by Kuba on 2018-01-14.
 */
public class Director {

    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Client getClient() {
        return builder.getClient();
    }

    public void addClientToDatabase(String thePesel, String theCompanyName, String theFirstName,
                                    String theSurName, String theNIP) {
        builder.newClient();
        builder.setPesel(thePesel);
        builder.setCompanyName(theCompanyName);
        builder.setFirstName(theFirstName);
        builder.setSurName(theSurName);
        builder.setNIP(theNIP);
    }
}
