package javaeems.chapter16.monitoredprimes.beans;

import javaeems.chapter16.monitoredprimes.web.Calculation;
import javaeems.chapter16.monitoredprimes.web.MonitorBroadcaster;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import java.util.*;
import javax.enterprise.concurrent.ManagedTask;

@Singleton
@LocalBean
public class MonitorBean {

    private final List<MonitorBroadcaster> listeners = new ArrayList<>();

    public void add(MonitorBroadcaster pbe) {
        this.listeners.add(pbe);
    }

    public void remove(MonitorBroadcaster pbe) {
        this.listeners.remove(pbe);
    }

    public void calculationStarted(int numberCalculations) {
        for (MonitorBroadcaster pbe : this.listeners) {
            pbe.sendUpdate("xx");
            pbe.sendUpdate("iCalculation started with " + numberCalculations + " calculations.");
        }
    }

    public void taskAborted(Object task) {
        Calculation calculation = (Calculation) task;
        for (MonitorBroadcaster pbe : this.listeners) {
            pbe.sendUpdate("iError in Task: " + calculation.getExecutionProperties().get(ManagedTask.IDENTITY_NAME));
        }
    }

    public void taskCompleted(Object task) {
        for (MonitorBroadcaster pbe : this.listeners) {
            if (task instanceof Calculation) {
                Calculation c = (Calculation) task;
                String prefix;
                if (c.getExecutionProperties().get(Calculation.EXECUTION_TYPE).equals(Calculation.CONCURRENT)) {
                    prefix = "c";
                } else {
                    prefix = "s";
                }
                long submitTime = Long.parseLong(c.getExecutionProperties().get(Calculation.SUBMIT_TIME_KEY));
                long startTime = Long.parseLong(c.getExecutionProperties().get(Calculation.START_TIME_KEY));
                long endTime = Long.parseLong(c.getExecutionProperties().get(Calculation.END_TIME_KEY));
                int id = Integer.parseInt(c.getExecutionProperties().get(ManagedTask.IDENTITY_NAME));
                pbe.sendUpdate(prefix + "Task(" + id + ") completed in " + (endTime - startTime) + "ms.");
            } else {
                pbe.sendUpdate(task.toString());
            }
        }

    }
}
