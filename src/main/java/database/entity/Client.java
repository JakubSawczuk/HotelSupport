package database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kuba on 2017-12-19.
 */
@Entity
public class Client {

    @Id
    private
    String Pesel;

    private String firstName;
    private String surName;
    private String companyName;
    private String NIP;

    public Client() {
    }

    public String getPesel() {
        return Pesel;
    }

    public void setPesel(String pesel) {
        Pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public void show(){

        if(Pesel != null) System.out.println("Pesel: " + Pesel);
        if(firstName != null) System.out.println("Imie: " + firstName);
        if(surName != null) System.out.println("Nazwisko: " + surName);
        if(companyName != null) System.out.println("Firma: " + companyName);
        if(NIP != null) System.out.println("NIP: " + NIP);
    }
}
