package events;

import database.entity.Client;
import database.entity.Invoice;
import database.entity.Room;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Kuba on 2018-01-28.
 */
public class InvoiceAddedEvent extends Event {
    public static final EventType<InvoiceAddedEvent> ADD_INVOICE_EVENT_EVENT_TYPE = new EventType<>("ADD_INVOICE_EVENT");
    private Invoice invoice;
    private Room room;
    private Client client;

    public InvoiceAddedEvent(EventType<? extends Event> eventType, Invoice invoice, Room room, Client client) {
        super(eventType);
        this.invoice = invoice;
        this.room = room;
        this.client = client;
    }
}
