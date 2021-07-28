package javaeems.chapter10.primes;

import javax.ejb.AsyncResult;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Asynchronous;
import javax.annotation.Resource;
import java.util.concurrent.Future;


@Stateless
public class PrimeCalculator implements PrimeCalculatorRemote {
    @Resource
    SessionContext context;
    
    @Asynchronous
    public Future<Long> calculateMaxPrimeBelow(long upperLimit) {
        Long current = null;

        for (long candidate = 2; candidate < upperLimit+1; candidate++) {
            if (isPrime(candidate)) {
                current = candidate;
            }
            if (context.wasCancelCalled()) {
                System.out.println("Cancel was called....");
                break;
            };
        }
        return new AsyncResult<>(current);
 
    }
    
    private boolean isPrime(long l) {
        for (long i = 2; i <= (long) l/2; i++) {
            if ((l % i) == 0) {
                return false;
            } 
        }
        return true;
    }
    
}
