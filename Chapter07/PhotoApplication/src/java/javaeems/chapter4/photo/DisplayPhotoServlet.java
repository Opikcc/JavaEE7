package javaeems.chapter4.photo;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;


@WebServlet(name = "DisplayPhotoServlet", urlPatterns = {"/main/DisplayPhotoServlet"})
public class DisplayPhotoServlet extends HttpServlet {
    PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbum();
    @Inject
    EditPhoto uploadBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("called");
        byte[] photoData;
        String indexString = request.getParameter("photoid");
        if (indexString != null) {
            long id = (new Long(indexString.trim())).longValue();
            Photo photo = photoAlbum.getPhoto(id);
            photoData = photo.getData();
        } else {
            photoData = uploadBean.getPhotoData();
        }
        response.setContentType("image/jpeg");
        try (OutputStream out = response.getOutputStream()) {
            for (int i = 0; i < photoData.length; i++) {
                out.write( photoData[i]);
            }
        } 
    }

}
