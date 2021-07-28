package javaeems.chapter9.hello.webclient;

import java.io.IOException;
import java.io.PrintWriter;
import javaeems.chapter9.hello.HelloBean;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletClient", urlPatterns = {"/ServletClient"})
public class ServletClient extends HttpServlet {
    @EJB
    private HelloBean helloBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello Java EE</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br>");
            out.println("<div align='center'>");
            out.println("<h2>Hello Enterprise Beans</h2>");
            out.println("<br>");
            String displayMessage;
            String message = helloBean.getMessageFor("web client");
            out.println("Result of calling the enterprise bean: <br><b>" 
                                            + message + "</b>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
}
