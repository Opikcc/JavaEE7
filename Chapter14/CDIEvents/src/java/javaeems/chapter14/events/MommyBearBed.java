package javaeems.chapter14.events;

import javax.enterprise.event.Event;
import javax.inject.Inject;

@Comfort("yielding")
public class MommyBearBed implements Bed {

    @Inject
    Event<SomeoneInBedEvent> sibe;

    @Override
    public String tryIt(String name) {
        sibe.fire(new SomeoneInBedEvent(this, name));
        return "too soft";
    }

    @Override
    public String toString() {
        return "Mommy's bed";
    }
}
