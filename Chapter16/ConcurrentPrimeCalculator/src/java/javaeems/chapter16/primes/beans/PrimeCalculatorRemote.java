package javaeems.chapter16.primes.beans;

import javax.ejb.Remote;

@Remote
public interface PrimeCalculatorRemote {
    
    public Long calculateMaxPrimeBelow(long l);
    
}
