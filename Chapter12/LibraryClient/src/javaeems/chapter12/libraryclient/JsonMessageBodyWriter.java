package javaeems.chapter12.libraryclient;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.Produces;
import javax.json.JsonObject;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;
import java.io.OutputStream;
import java.io.IOException;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonMessageBodyWriter implements MessageBodyWriter<JsonObject> {
    
    @Override
    public boolean isWriteable(Class<?> type,
                  Type genericType,
                  Annotation[] annotations,
                  MediaType mediaType) {
        return true;
    }
    
    @Override
    public long getSize(JsonObject t,
           Class<?> type,
           Type genericType,
           Annotation[] annotations,
           MediaType mediaType) {
        return -1; //ignored
    }
    
    @Override
    public void writeTo(JsonObject t,
           Class<?> type,
           Type genericType,
           Annotation[] annotations,
           MediaType mediaType,
           MultivaluedMap<String,Object> httpHeaders,
           OutputStream entityStream)
             throws IOException,
                    WebApplicationException {
        
        String s = t.toString();
        byte[] bytes = s.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            entityStream.write(bytes[i]);
        }
    }
    
    
}
