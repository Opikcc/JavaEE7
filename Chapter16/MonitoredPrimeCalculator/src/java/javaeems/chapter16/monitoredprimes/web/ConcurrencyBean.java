package javaeems.chapter16.monitoredprimes.web;

import javaeems.chapter16.monitoredprimes.beans.MonitorBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.*;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import java.util.concurrent.Future;
import javax.ejb.*;
import java.io.*;
import java.util.concurrent.ExecutionException;

@Named("concurrencyBean")
@SessionScoped
public class ConcurrencyBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    MonitorBean mb;
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
        this.mb.calculationStarted(this.numberCalculations);
        this.getSequentialResults();
        this.getParallelResults();
    }

    public List<Long> getSequentialResults() {

        if (this.sequentialResults != null) {
            return this.sequentialResults;
        }
        this.sequentialResults = new ArrayList<>();
        long then = System.currentTimeMillis();
        int id = 0;
        for (Long upperBound : this.getUpperBounds()) {
            Calculation c = new Calculation(mb, id++, upperBound, Calculation.SEQUENTIAL);
            c.getExecutionProperties().put(Calculation.SUBMIT_TIME_KEY, Long.toString(System.currentTimeMillis()));
            c.getExecutionProperties().put(Calculation.START_TIME_KEY, Long.toString(System.currentTimeMillis()));
            long l = c.call();
            c.getExecutionProperties().put(Calculation.END_TIME_KEY, Long.toString(System.currentTimeMillis()));
            this.mb.taskCompleted(c);
            this.sequentialResults.add(c.call());
        }
        this.lastSequentialTime = System.currentTimeMillis() - then;
        return this.sequentialResults;
    }

    public List<Long> getUpperBounds() {
        if (this.upperBounds == null) {
            this.upperBounds = new ArrayList<>();
            for (int i = 0; i < numberCalculations; i++) {
                double d = Math.random();
                long nextUpperBound = (long) (2 + (d * (maxUpperBound - 2)));
                upperBounds.add(nextUpperBound);
            }
        }
        return this.upperBounds;
    }

    public Long getLastParallelTime() {
        return this.lastParallelTime;
    }

    public List<Long> getParallelResults() {
        if (parallelResults != null) {
            return this.parallelResults;
        }
        this.parallelResults = new ArrayList<>();

        List<Calculation> calculations = new ArrayList<>();
        int id = 0;
        for (Long upperBound : this.getUpperBounds()) {
            Calculation c = new Calculation(mb, id++, upperBound, Calculation.CONCURRENT);
            calculations.add(c);
        }
        try {
            long then = System.currentTimeMillis();
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
