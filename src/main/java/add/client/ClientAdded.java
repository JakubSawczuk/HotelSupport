package add.client;

/**
 * Created by Kuba on 2018-01-14.
 */
public class ClientAdded extends BuilderClient {

    public void setPesel(String pesel) {
        client.setPesel(pesel);
    }

    public void setFirstName(String firstName) {
        client.setFirstName(firstName);
    }

    public void setSurName(String surName) {
        client.setSurName(surName);
    }

    public void setNIP(String nip) {
        client.setNIP(nip);
    }

    public void setCompanyName(String companyName) {
        client.setCompanyName(companyName);
    }
}
