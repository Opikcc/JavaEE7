package javaeems.chapter16.primes.simple;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.*;
import javax.annotation.Resource;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import javax.enterprise.concurrent.ManagedExecutorService;

@Named("concurrencyBean")
@RequestScoped
public class ConcurrencyBean {

    @Resource
    ManagedExecutorService executor;
    private int numberCalculations = 5;
    private long lastParallelTime;
    private long lastSequentialTime;

    private List<Long> sequentialResults = null;
    private List<Long> parallelResults = null;
    private List<Long> upperBounds = null;
    private int maxUpperBound = 1000;

    public void reset() {
        this.lastParallelTime = 0;
        this.lastSequentialTime = 0;
        this.sequentialResults = null;
        this.parallelResults = null;
        this.upperBounds = null;
    }

    public int getMaxUpperBound() {
        return this.maxUpperBound;
    }

    public void setMaxUpperBound(int maxUpperBound) {
        this.maxUpperBound = maxUpperBound;
    }

    public int getNumberCalculations() {
        return this.numberCalculations;
    }

    public void setNumberCalculations(int numberCalculations) {
        this.numberCalculations = numberCalculations;
        this.reset();
    }

    public void doCalculate() {
        this.getSequentialResults();
        this.getParallelResults();
    }

    public List<Long> getSequentialResults() {
        if (this.sequentialResults != null) {
            return this.sequentialResults;
        }
        this.sequentialResults = new ArrayList<>();
        long then = System.currentTimeMillis();
        for (Long upperBound : this.getUpperBounds()) {
            PrimeCalculation c = new PrimeCalculation();
            c.setUpperBound(upperBound);
            this.sequentialResults.add(c.call());
        }
        this.lastSequentialTime = System.currentTimeMillis() - then;
        return this.sequentialResults;
    }

    public List<Long> getUpperBounds() {
        if (this.upperBounds == null) {
            this.upperBounds = new ArrayList<>();
            for (int i = 0; i < this.numberCalculations; i++) {
                double d = Math.random();
                long nextUpperBound = (long) (2 + (d * (this.maxUpperBound - 2)));
                this.upperBounds.add(nextUpperBound);
            }
        }
        return this.upperBounds;
    }

    public Long getLastParallelTime() {
        return this.lastParallelTime;
    }

    public List<Long> getParallelResults() {
        if (this.parallelResults != null) {
            return this.parallelResults;
        }
        this.parallelResults = new ArrayList<>();
        long then = System.currentTimeMillis();
        List<PrimeCalculation> calculations = new ArrayList<>();
        for (Long upperBound : this.getUpperBounds()) {
            PrimeCalculation c = new PrimeCalculation();
            c.setUpperBound(upperBound);
            calculations.add(c);
        }
        try {
            List<Future<Long>> resultList = executor.invokeAll(calculations);
            for (Future<Long> next : resultList) {
                this.parallelResults.add(next.get());
            }
            this.lastParallelTime = System.currentTimeMillis() - then;
            return this.parallelResults;
        } catch (InterruptedException e) {
            System.out.println("The executor encountered an error making the calculation: " + e.getMessage());
        } catch (ExecutionException ee) {
            System.out.println("The calculation threw an error: " + ee.getMessage());
        }
        return new ArrayList<>();
    }

    public Long getLastSequentialTime() {
        return this.lastSequentialTime;
    }

}
