package javaeems.chapter14.sleepscopes;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class BunkBed implements Bed, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String tryIt() {
        return "quit moving around !";
    }
}
