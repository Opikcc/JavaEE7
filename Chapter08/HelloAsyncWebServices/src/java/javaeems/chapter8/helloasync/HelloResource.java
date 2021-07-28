package javaeems.chapter8.helloasync;

import java.text.SimpleDateFormat;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.*;

@Path("hello")
public class HelloResource {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm:ss a");
    
    
    @PUT
    @Produces("text/plain")
    public void sayHelloPlain(@Suspended final AsyncResponse asyncResponse, 
                                                    final String requestEntity) {
        final Date requestTime = new Date();
        Thread workerThread = new Thread() {
            @Override
            public void run() {
                try {Thread.sleep(2000);} catch (Exception r) {}
                asyncResponse.resume(processResponse(requestTime, requestEntity));
            }
        };
        workerThread.start();
    }

    private String processResponse(Date requestTime, String requestEntity) {
        return "Replying to your message ("+ requestEntity +") I got at " + dateFormatter.format(requestTime);
    }
    
}
