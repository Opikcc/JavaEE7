package javaeems.chapter4.photo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

@Named(value = "securityBean")
@RequestScoped
public class SecurityBean {
    private static String VIEWER_ROLE = "viewer";
    private static String PHOTOGRAPHER_ROLE = "photographer";
    
    private HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public String getUsername() {
        HttpServletRequest request = this.getHttpServletRequest();
        if (request.getUserPrincipal() != null) {
            return request.getUserPrincipal().getName();
        } else {
            return "";
        }
    }
    
    public boolean isUserAbleToEdit() {
        HttpServletRequest request = this.getHttpServletRequest();
        return request.isUserInRole(PHOTOGRAPHER_ROLE);
    }
    
    public boolean isLoggedIn() {
        return (this.getHttpServletRequest().getUserPrincipal() != null);
    }
    
    public void logout() {
        try {
            this.getHttpServletRequest().logout();
            this.getHttpServletRequest().getSession().invalidate();
            System.out.println("logged out");
        } catch (ServletException se) {
            System.out.println("ecxception logging out " + se.getMessage());
        }
    }
    
}
