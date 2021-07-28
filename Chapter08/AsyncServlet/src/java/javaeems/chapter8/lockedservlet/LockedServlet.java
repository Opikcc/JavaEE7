package javaeems.chapter8.lockedservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;

@WebServlet(urlPatterns = {"/LockedServlet"}, asyncSupported=true)
public class LockedServlet extends HttpServlet {
    public static String LOCK = "lock";
    public static String LOCKED = "locked";
    public static String UNLOCKED = "unlocked";

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final AsyncContext asyncContext = request.startAsync();
        
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                performProcessing(asyncContext);
            }
        });
        
        ServletContext context = request.getServletContext();
        context.setAttribute(LOCK, LOCKED);          
    }
    
    protected void performProcessing(AsyncContext asyncContext) {
        HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
        response.setContentType("text/html");
        HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
        ServletContext context = request.getServletContext();
        int wait = 0;
        for (wait = 0; wait < 100; wait++) {
            if (!"locked".equals(context.getAttribute(LOCK))) {
                break;
            } else {
                try {
                    Thread.sleep(250);
                } catch (Exception r) {}
            }  
        }
        int waitSecs = (int) (wait /4);
        try (PrintWriter out = response.getWriter()){                  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + waitSecs + " seconds</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<image src='unlock_lg.png'/>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ioe) {
            System.out.println("There was an IOException");
        } 
        asyncContext.complete();
        
    }

}
