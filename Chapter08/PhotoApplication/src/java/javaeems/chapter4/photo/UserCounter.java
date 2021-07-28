package javaeems.chapter4.photo;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

@WebListener
public class UserCounter implements HttpSessionListener {
    private List<HttpSession> sessions = new ArrayList<>();
    public static String SERVLET_CONTEXT_SESSION_LIST = "servlet_context_session_list";
    

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        this.sessions.add(se.getSession());
        this.updateServletContext(se.getSession().getServletContext());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        this.sessions.remove(se.getSession());
        this.updateServletContext(se.getSession().getServletContext());
    }
    
    private void updateServletContext(ServletContext sc) {
        sc.setAttribute(SERVLET_CONTEXT_SESSION_LIST, new ArrayList(sessions));
 
    }
}
