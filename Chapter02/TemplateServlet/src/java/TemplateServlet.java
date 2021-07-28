import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TemplateServlet", urlPatterns = {"/TemplateServletURI"})
public class TemplateServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        for (Enumeration e = request.getHeaderNames(); e.hasMoreElements(); ) {
            String nextRequestHeaderName = (String) e.nextElement();
            String nextRequestHeaderValue = request.getHeader(nextRequestHeaderName);
            // 1. Examine request headers 
        }
        
        // 2. Read request body
        InputStream is = request.getInputStream(); 
        // or
        Reader reader = request.getReader();
        
        // 3. Gather data to send back
        
        // 4. Set response headers
        response.setHeader(myResponseHeaderName, myResponseHeaderValue);

        // 5. Write response body
        PrintWriter writer = response.getWriter();
        // or
        OutputStream output = response.getOutputStream();
        
    }

}
