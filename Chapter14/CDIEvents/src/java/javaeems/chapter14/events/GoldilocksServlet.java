package javaeems.chapter14.events;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.inject.Inject;

@WebServlet(name = "GoldilocksServlet", urlPatterns = {"/GoldilocksServlet"})
public class GoldilocksServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private Bed bed3;

    @Inject
    @Comfort("firm")
    private Bed bed1;

    private Bed bed2;

    @Inject
    public void initializeBed(@Comfort("yielding") Bed bean) {
        this.bed2 = bean;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = "Goldilocks";
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
            out.println("Goldilocks tried the first bed, but it was  " + bed1.tryIt(name) + ".<br><br>");
            out.println("Goldilocks tried the second bed, but it was  " + bed2.tryIt(name) + ".<br><br>");
            out.println("Goldilocks tried the third bed, and it was  " + bed3.tryIt(name) + ".<br><br>");
            out.println("<a href='GrandparentsServlet'>What do the grandparents know ?</a><br><br>");
            out.println("</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
