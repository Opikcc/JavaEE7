package javaeems.chapter14.events;

import java.util.*;

public class RememberingBear {

    List<SomeoneInBedEvent> events = new ArrayList<>();

    public List<SomeoneInBedEvent> getEvents() {
        return events;
    }
}
