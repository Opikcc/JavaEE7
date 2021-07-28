package javaeems.chapter16.monitoredprimes.web;

import javaeems.chapter16.monitoredprimes.beans.MonitorBean;
import java.util.concurrent.Callable;
import javaeems.chapter16.monitoredprimes.beans.PrimeCalculatorRemote;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

public class Calculation implements Callable<Long>, ManagedTask {

    public static String SUBMIT_TIME_KEY = "SUBMIT_TIME_KEY";
    public static String START_TIME_KEY = "START_TIME_KEY";
    public static String END_TIME_KEY = "END_TIME_KEY";
    public static String EXECUTION_TYPE = "EXECUTION_TYPE";
    public static String SEQUENTIAL = "sequential";
    public static String CONCURRENT = "concurrent";

    private final long upperBound;
    private final MonitorBean mb;
    private final Map<String, String> executionProperties = new HashMap<>();

    public Calculation(MonitorBean mb, int id, long upperBound, String type) {
        this.mb = mb;
        this.upperBound = upperBound;
        this.executionProperties.put(EXECUTION_TYPE, type);
        this.executionProperties.put(ManagedTask.IDENTITY_NAME, Integer.toString(id));
    }

    @Override
    public Long call() {
        try {
            PrimeCalculatorRemote primeCalculator = InitialContext.doLookup("java:global/MonitoredPrimeCalculator/PrimeCalculator");
            return primeCalculator.calculateMaxPrimeBelow(upperBound);
        } catch (NamingException ne) {
            System.out.println(ne.getMessage());
            return (long) -1;
        }
    }

    @Override
    public ManagedTaskListener getManagedTaskListener() {
        return new CalculationListener(this.mb);
    }

    @Override
    public Map<String, String> getExecutionProperties() {
        return this.executionProperties;
    }

}
