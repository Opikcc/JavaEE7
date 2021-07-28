package javaeems.chapter14.sleepscopes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.*;

@WebServlet(urlPatterns = {"/SleepScopesServlet"})
public class SleepScopesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    @Inexpensive
    Bed bedA;
    @Inject
    @Comfortable
    Bed bedB;
    @Inject
    Bed bedC;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sleep test servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div align='center'>");
            out.println("<h2>Three beds to sleep in, but which one has which scope ? </h2>");
            out.println("<h2>Request scope, Session scope or Application scope ? </h2>");
            out.println("<p>");
            out.println("Bed A " + bedA + " <br><br>");
            out.println("Bed B " + bedB + " <br><br>");
            out.println("Bed C " + bedC + " <br><br>");
            out.println("</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
