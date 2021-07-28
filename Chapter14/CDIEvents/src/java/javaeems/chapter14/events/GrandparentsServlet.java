package javaeems.chapter14.events;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.inject.Inject;
import java.util.List;

@WebServlet(urlPatterns = {"/GrandparentsServlet"})
public class GrandparentsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    GrandmotherBear grandmotherBear;
    @Inject
    GrandfatherBear grandfatherBear;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.getSession().invalidate();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Goldilocks and the CDI beans</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div align='center'>");
            out.println("<h2>CDI beans with events</h2>");
            out.println("<img width = '80' height='80' src='bear-icon.png'></img>");
            out.println("<p>");

            out.println("Grandmother bear knows: <br><br>");
            this.printMemory(grandmotherBear.getEvents(), out);
            out.println("<br><br>");
            out.println("Grandfather bear knows ");
            this.printMemory(grandfatherBear.getEvents(), out);

            out.println("</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void printMemory(List<SomeoneInBedEvent> events, PrintWriter out) {
        if (events.isEmpty()) {
            out.print("nothing, he was asleep !");
        }
        for (SomeoneInBedEvent e : events) {
            out.print(e.getName() + " tried " + e.getBed() + "<br>");
        }
    }

}
