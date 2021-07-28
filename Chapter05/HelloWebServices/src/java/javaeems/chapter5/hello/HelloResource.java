package javaeems.chapter5.hello;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.HttpHeaders;

@Path("hello")
public class HelloResource {
    
    @GET
    @Produces("text/html")
    public String sayHello(@Context UriInfo uri, 
                            @Context HttpHeaders headers) {
        return "<html>" +
                "    <head>" +
                "        <title></title>" +
                "        <meta http-equiv='Content-Type' content='text/html'>" +
                "    </head>" +
                "    <body>" +
                "        <div align='center'>Hello, I am a web service resource," +
                "           and here is some of my info. <br></br> I was accessed at <b>" 
                                                + uri.getAbsolutePath() + "</b></div><br>" +
                "             <div align='center'>Here are the request headers<br></br> "    +                            
                "        <b>" + this.writeHeaders(headers) + "</b></div>" +
                
                "    </body>\n" +
                "</html>";
        
    }
    
    private String writeHeaders(HttpHeaders headers) {

        StringBuilder buf = new StringBuilder();
        for (String header: headers.getRequestHeaders().keySet()) {
            buf.append(header);
            buf.append(":");
            buf.append(headers.getRequestHeader(header));
            buf.append("<br>");
        }
        return buf.toString();
    }
    
    @PUT
    @Produces("text/plain")
    public String sayHelloPlain(String requestEntity) {
        return "Hello " + requestEntity + ", from JAX-RS land !";
        
    }
    
}
