package events;

import database.entity.Room;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Kuba on 2018-01-28.
 */
public class EditRoomEvent extends Event {

    public static final EventType<EditRoomEvent> EDIT_ROOM_EVENT_EVENT_TYPE= new EventType<>("EDIT_ROOM_EVENT");
    private Room room;

    public EditRoomEvent(EventType<? extends Event> eventType, Room room) {
        super(eventType);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
