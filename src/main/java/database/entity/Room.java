package database.entity;

import com.sun.istack.internal.NotNull;
import modificationroom.RoomI;

import javax.persistence.*;

/**
 * Created by Kuba on 2017-12-19.
 */
@Entity
public class Room implements RoomI{

    @Id
    int numberRoom;

    boolean isAvailable,
             isClear;
    String comfort;
    int capacity;
    float price;

    @ManyToOne
    @JoinColumn(name = "invoices")
    Invoice invoice;

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void display() {

    }
}
