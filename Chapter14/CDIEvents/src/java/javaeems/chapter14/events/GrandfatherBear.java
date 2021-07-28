package javaeems.chapter14.events;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class GrandfatherBear extends RememberingBear implements Serializable {

    private static final long serialVersionUID = 1L;

    public void listen(@Observes(notifyObserver = Reception.IF_EXISTS) SomeoneInBedEvent whbimbe) {
        events.add(whbimbe);
    }
}
