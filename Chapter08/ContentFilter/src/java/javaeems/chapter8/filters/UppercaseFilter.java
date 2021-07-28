package javaeems.chapter8.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.security.Principal;


@WebFilter(filterName = "Uppercase", urlPatterns = {"*.html"})
public class UppercaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        
        Principal principal = ((HttpServletRequest) request).getUserPrincipal();
        PrintWriter writer = new PrintWriter(response.getWriter());
        writer.println("<p><b>The page is filtered for: " + principal.getName() + "</b></p>");
        try {
            UppercaseResponse uResponse = new UppercaseResponse((HttpServletResponse) response);
            chain.doFilter(request, uResponse);
        } catch (Throwable t) {
            throw new ServletException("Error during filtering: " + t.getMessage());
        }  
    }


    @Override
    public void destroy() {        
    }


}

