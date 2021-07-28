package javaeems.chapter14.events;

public class SomeoneInBedEvent {

    private final String name;
    private final Bed bed;

    public SomeoneInBedEvent(Bed bed, String name) {
        this.bed = bed;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Bed getBed() {
        return this.bed;
    }

}
