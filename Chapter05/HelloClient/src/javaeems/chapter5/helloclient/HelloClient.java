package javaeems.chapter5.helloclient;

import javax.ws.rs.client.*;

public class HelloClient {
    WebTarget wt;
    
    public HelloClient() {
        Client client = ClientBuilder.newClient();
        this.wt = client.target("http://localhost:8080/HelloWebServices/hello");
    }
    
    public String getUriAsString() {
        return this.wt.getUri().toString();
    }
    
    public String sayHello(String message) {
        Invocation webServiceCall = wt.request()
                .accept("text/plain")
                .build("PUT", Entity.text(message));
        String s = webServiceCall.invoke(String.class);
        return s;
    }

}
