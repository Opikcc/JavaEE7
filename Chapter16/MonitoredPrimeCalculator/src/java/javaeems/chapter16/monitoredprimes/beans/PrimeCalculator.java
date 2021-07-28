package javaeems.chapter16.monitoredprimes.beans;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.annotation.Resource;

@Stateless
public class PrimeCalculator implements PrimeCalculatorRemote {

    @Resource
    SessionContext context;

    @Override
    public Long calculateMaxPrimeBelow(long upperLimit) {
        Long current = null;
        for (long candidate = 2; candidate < upperLimit + 1; candidate++) {
            if (isPrime(candidate)) {
                current = candidate;
            }
        }
        return current;

    }

    private boolean isPrime(long l) {
        for (long i = 2; i <= (long) l / 2; i++) {
            if ((l % i) == 0) {
                return false;
            }
        }
        return true;
    }

}
