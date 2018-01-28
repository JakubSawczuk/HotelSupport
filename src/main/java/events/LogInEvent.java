package events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Kuba on 2018-01-28.
 */
public class LogInEvent extends Event{

    public static final EventType<LogInEvent> LOG_IN_EVENT_EVENT_TYPE= new EventType<>("LOG_IN_EVENT");

    public LogInEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

}
