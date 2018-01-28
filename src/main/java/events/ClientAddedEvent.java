package events;

import database.entity.Client;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Kuba on 2018-01-28.
 */
public class ClientAddedEvent extends Event {
    public static final EventType<ClientAddedEvent> CLIENT_ADDED_EVENT_EVENT_TYPE = new EventType<>("CLIENT_ADDED_EVENT");
    private Client client;

    public ClientAddedEvent(EventType<? extends Event> eventType, Client client) {
        super(eventType);
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
