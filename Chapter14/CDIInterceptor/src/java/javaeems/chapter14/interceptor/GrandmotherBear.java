package javaeems.chapter14.interceptor;

import javax.enterprise.event.Observes;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class GrandmotherBear extends RememberingBear implements Serializable {

    public void listen(@Observes SomeoneInBedEvent whbimbe) {
        events.add(whbimbe);
    }

}
