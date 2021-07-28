package javaeems.chapter7.servletannotation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.HttpMethodConstraint;



@WebServlet(name = "UploadPhoto", urlPatterns = {"/photo/upload"})
@MultipartConfig()
@ServletSecurity(httpMethodConstraints={
    @HttpMethodConstraint(
        value="GET",
        emptyRoleSemantic=ServletSecurity.EmptyRoleSemantic.PERMIT),
    @HttpMethodConstraint(
        value="POST",
        rolesAllowed={"administrator", "photographer"},
        transportGuarantee=ServletSecurity.TransportGuarantee.CONFIDENTIAL)
    })
public class UploadPhoto extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // create the photo catalog page
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // upload a photo
    }


}
