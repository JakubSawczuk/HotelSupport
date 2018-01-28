package database.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Created by Kuba on 2017-12-19.
 */

// musze poczytac o mapped by w OneToMany
@Entity
public class Invoice {

    @GeneratedValue
    @Id
    private int idInvoice;

    private LocalDateTime dateInsue;

    private LocalDateTime dateExpiration;

    private int howManyDays;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Room room;

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public LocalDateTime getDateInsue() {
        return dateInsue;
    }

    public void setDateInsue(LocalDateTime dateInsue) {
        this.dateInsue = dateInsue;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }


    public int getHowManyDays() {
        return howManyDays;
    }

    public void setHowManyDays(int howManyDays) {
        this.howManyDays = howManyDays;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
