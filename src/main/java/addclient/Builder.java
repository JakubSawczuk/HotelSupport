package addclient;

import database.entity.Client;

/**
 * Created by Kuba on 2018-01-14.
 */
// budowniczy
public abstract class Builder {

    protected Client client;

    public void newClient(){
        client = new Client();
    }

    public Client getClient(){
        return client;
    }

    public abstract void setPesel(String pesel);
    public abstract void setFirstName(String firstName);
    public abstract void setSurName(String surName);
    public abstract void setNIP(String nip);
    public abstract void setCompanyName(String companyName);
}
