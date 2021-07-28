package javaeems.chapter12.library;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.Consumes;
import javax.json.Json;
import javax.json.JsonObject;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonMessageBodyReader implements MessageBodyReader<JsonObject> {

    @Override
    public boolean isReadable(java.lang.Class<?> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType) {
        return true;

    }

    @Override
    public JsonObject readFrom(java.lang.Class<JsonObject> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<java.lang.String, java.lang.String> httpHeaders,
            java.io.InputStream entityStream)
            throws java.io.IOException,
            WebApplicationException {

        JsonObject o = Json.createReader(entityStream).readObject();
        System.out.println(o);
        return o;

    }

}
