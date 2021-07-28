package javaeems.chapter16.monitoredprimes.web;

import javaeems.chapter16.monitoredprimes.beans.MonitorBean;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import javax.naming.*;

@ServerEndpoint("/progress")
public class MonitorBroadcaster {

    private Session session;
    private MonitorBean mb;

    @OnOpen
    public void startMonitoring(Session session) {
        try {
            this.mb = InitialContext.doLookup("java:global/MonitoredPrimeCalculator/MonitorBean");
            this.session = session;
            mb.add(this);
        } catch (NamingException ne) {
            System.out.println(ne.getMessage());
        }
    }

    public void sendUpdate(String hi) {
        try {
            this.session.getBasicRemote().sendText(hi);
        } catch (IOException ioe) {
            System.out.println("error notifying client: " + ioe.getMessage());
        }
    }

    @OnError
    public void monitoringError(Throwable t) {
        System.out.println("Error in boradcaster: " + t.getMessage());
        this.mb.remove(this);
    }

    @OnClose
    public void stopMonitoring() {
        this.mb.remove(this);
    }

}
