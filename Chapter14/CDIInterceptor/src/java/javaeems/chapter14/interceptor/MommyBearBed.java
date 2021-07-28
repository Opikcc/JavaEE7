package javaeems.chapter14.interceptor;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Comfort("yielding")
public class MommyBearBed implements Bed {

    @Inject
    Event<SomeoneInBedEvent> sibe;

    @Override
    @Interceptors(BuggingDevice.class)
    public String tryIt(String name) {
        sibe.fire(new SomeoneInBedEvent(this, name));
        return "too soft";
    }

    @Override
    public String toString() {
        return "Mommy's bed";
    }
}
