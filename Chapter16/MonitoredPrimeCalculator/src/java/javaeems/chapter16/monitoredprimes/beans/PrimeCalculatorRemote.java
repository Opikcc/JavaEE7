package javaeems.chapter16.monitoredprimes.beans;

import javax.ejb.Remote;

@Remote
public interface PrimeCalculatorRemote {

    public Long calculateMaxPrimeBelow(long l);
}
