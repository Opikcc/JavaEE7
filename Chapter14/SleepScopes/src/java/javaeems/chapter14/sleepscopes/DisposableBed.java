package javaeems.chapter14.sleepscopes;

import javax.enterprise.context.RequestScoped;

@RequestScoped
@Inexpensive
public class DisposableBed implements Bed {

    @Override
    public String tryIt() {
        return "it's uncomfortable";
    }
}
