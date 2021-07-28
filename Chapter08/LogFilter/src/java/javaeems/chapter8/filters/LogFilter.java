package javaeems.chapter8.filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.*;


//@WebFilter(filterName = "LogFilter", urlPatterns = {"/*"})
public class LogFilter implements Filter {
    private Logger logger;
    @Override
    public void init(FilterConfig filterConfig) {        
         this.logger = Logger.getLogger(LogFilter.class.getName());
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        
        this.logger.log(Level.INFO, "Request for " + 
                ((HttpServletRequest)request).getRequestURI());
        try {
            chain.doFilter(request, response);
        } catch (ServletException se) {
            this.logger.log(Level.SEVERE, "Error fulfilling request " + 
                    se.getMessage());
            throw new ServletException("Error invoking the " +
                    "rest of the filter chain: " + se.getMessage());
        } 
        this.logger.log(Level.INFO, "Request complete.");
    }

    @Override
    public void destroy() { 
        this.logger = null;
    }

}


