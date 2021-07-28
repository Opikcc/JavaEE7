package javaeems.chapter14.interceptor;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Comfort("firm")
public class DaddyBearBed implements Bed {

    @Inject
    Event<SomeoneInBedEvent> sibe;

    @Override
    @Interceptors(BuggingDevice.class)
    public String tryIt(String name) {
        sibe.fire(new SomeoneInBedEvent(this, name));
        return "too hard";
    }

    @Override
    public String toString() {
        return "Daddy's bed";
    }

}
