package javaeems.chapter14.golidlocks;

import javax.annotation.PreDestroy;

@Comfort("firm")
public class DaddyBearBed implements Bed {

    @Override
    public String tryIt() {
        return "too hard";
    }

    @PreDestroy
    public void doCleanup() {
        System.out.println("Bye: your bed was too hard in any case");
    }
}
