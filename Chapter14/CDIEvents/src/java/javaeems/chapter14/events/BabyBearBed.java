package javaeems.chapter14.events;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class BabyBearBed implements Bed {

    @Inject
    Event<SomeoneInBedEvent> sibe;

    @Override
    public String tryIt(String name) {
        sibe.fire(new SomeoneInBedEvent(this, name));
        return "just right";
    }

    @Override
    public String toString() {
        return "Baby's bed";
    }

}
