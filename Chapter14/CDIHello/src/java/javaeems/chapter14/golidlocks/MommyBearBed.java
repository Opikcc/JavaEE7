package javaeems.chapter14.golidlocks;

@Comfort("yielding")
public class MommyBearBed implements Bed {

    @Override
    public String tryIt() {
        return "too soft";
    }
}
