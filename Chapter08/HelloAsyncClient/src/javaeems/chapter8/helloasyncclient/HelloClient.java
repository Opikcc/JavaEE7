package javaeems.chapter8.helloasyncclient;

import javax.ws.rs.client.*;
import java.util.concurrent.Future;
import javax.ws.rs.core.Response;

public class HelloClient {
    WebTarget wt;
    
    public HelloClient() {
        Client client = ClientBuilder.newClient();
        this.wt = client.target("http://localhost:8080/HelloAsyncWebServices/hello");
    }
    
    public String getUriAsString() {
        return this.wt.getUri().toString();
    }
    
    public Future<Response> sayHelloFuture(String message) {
        Future<Response> futureResponse = wt
                .request()
                .accept("text/plain")
                .async()
                .method("PUT", Entity.text(message));
        return futureResponse; 
    }
      

}
