package javaeems.chapter14.interceptor;

import java.util.*;

public class RememberingBear {

    List<SomeoneInBedEvent> events = new ArrayList<>();

    public List<SomeoneInBedEvent> getEvents() {
        return events;
    }
}
