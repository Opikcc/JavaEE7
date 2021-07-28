package javaeems.chapter2.photos.basic;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DisplayPhotoServlet", urlPatterns = {"/DisplayPhotoServlet"})
public class DisplayPhotoServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String indexString = request.getParameter("photo");        
        int index = (new Integer(indexString.trim())).intValue();
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        try {
            ServletContext myServletContext = 
			request.getServletContext();

            PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(myServletContext);
            byte[] bytes = pa.getPhotoData(index);
            for (int i = 0; i < bytes.length; i++) {
                out.write( bytes[i]);
            }
        } finally {            
            out.close();
        } 
    }

}
