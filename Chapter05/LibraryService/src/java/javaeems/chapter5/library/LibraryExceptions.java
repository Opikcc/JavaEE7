
package javaeems.chapter5.library;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class LibraryExceptions implements ExceptionMapper<InvalidBookException> {
    
    @Override
    public Response toResponse(InvalidBookException exception) {
        Response r = Response.serverError()
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
        return r;
    }
}
