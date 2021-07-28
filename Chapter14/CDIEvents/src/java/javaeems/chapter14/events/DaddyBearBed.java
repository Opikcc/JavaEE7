package javaeems.chapter14.events;

import javax.enterprise.event.Event;
import javax.inject.Inject;

@Comfort("firm")
public class DaddyBearBed implements Bed {

    @Inject
    Event<SomeoneInBedEvent> sibe;

    @Override
    public String tryIt(String name) {
        sibe.fire(new SomeoneInBedEvent(this, name));
        return "too hard";
    }

    @Override
    public String toString() {
        return "Daddy's bed";
    }

}
