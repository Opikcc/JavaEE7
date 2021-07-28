package javaeems.chapter16.primes.simple;

import java.util.concurrent.Callable;
import javaeems.chapter16.primes.beans.PrimeCalculatorRemote;
import javax.naming.*;

public class PrimeCalculation implements Callable<Long> {

    private long upperBound = 10;

    public void setUpperBound(long upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public Long call() {
        try {
            PrimeCalculatorRemote primeCalculator = InitialContext.doLookup("java:global/ConcurrentPrimeCalculator/PrimeCalculator");
            return primeCalculator.calculateMaxPrimeBelow(upperBound);
        } catch (NamingException ne) {
            System.out.println(ne.getMessage());
            return (long) -1;
        }
    }

}
