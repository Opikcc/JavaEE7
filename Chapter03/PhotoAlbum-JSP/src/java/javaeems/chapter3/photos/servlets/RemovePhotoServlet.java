package javaeems.chapter3.photos.servlets;

import javaeems.chapter3.photos.beans.PhotoAlbum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RemovePhotoServlet", urlPatterns = {"/RemovePhotoServlet"})
public class RemovePhotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String indexString = request.getParameter("photo");        
        int index = (new Integer(indexString.trim())).intValue();
        HttpSession session = request.getSession();
        PhotoAlbum myPhotos = PhotoAlbum.getPhotoAlbum(session);
        myPhotos.removePhoto(index);
        response.sendRedirect("./album.jsp"); 
    }

}
