package database.entity;

import org.hibernate.type.LocalDateTimeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2017-12-19.
 */

// musze poczytac o mapped by w OneToMany
@Entity
public class Invoice {

    @GeneratedValue
    @Id
    private int idInvoice;

    private LocalDateTimeType dataInsue;

    private int howManyDays;

    @OneToMany(mappedBy = "invoice")
    //@JoinColumn(name = "id_invoice")
    private List<Room> roomList = new ArrayList<Room>();
}
