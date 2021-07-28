
package javaeems.chapter3.photos.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javaeems.chapter3.photos.beans.PhotoAlbum;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
@MultipartConfig()
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(session);
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            this.uploadPhoto(request, pa);
        }
        response.sendRedirect("./album.jsp");
    }
    
    private void uploadPhoto(HttpServletRequest request, PhotoAlbum pa) throws IOException, ServletException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String filename = null;
        for (Part p: request.getParts()) {
            this.copyBytes(p.getInputStream(), baos);
            filename = p.getSubmittedFileName();
         }
        if (!"".equals(filename)) {
         String photoName = filename.substring(0, filename.lastIndexOf("."));
         pa.addPhoto(photoName, baos.toByteArray());
        }
    }
      
    private void copyBytes(InputStream is, OutputStream os) throws IOException {
        int i;
        while ( (i=is.read()) != -1) {
            os.write(i);
        } 
        is.close();
        os.close();
    }

 
}
