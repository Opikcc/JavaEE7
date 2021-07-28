package javaeems.chapter14.sleepscopes;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
@Comfortable
public class RegularBed implements Bed, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String tryIt() {
        return "z z  z   z";
    }
}
