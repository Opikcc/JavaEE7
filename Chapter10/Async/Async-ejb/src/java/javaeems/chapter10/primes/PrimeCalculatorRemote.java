package javaeems.chapter10.primes;

import java.util.concurrent.Future;
import javax.ejb.Remote;

@Remote
public interface PrimeCalculatorRemote {
    
    public Future<Long> calculateMaxPrimeBelow(long l);
    
}
