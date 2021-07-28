<%-- 
    Document   : display-album
    Created on : Jan 27, 2014, 4:07:44 PM
    Author     : dannycoward
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

            <h3 align='center'>Photos</h3>
            <table align='center'>
                <%  
                    javaeems.chapter2.photos.advanced.PhotoAlbum pa = javaeems.chapter2.photos.advanced.PhotoAlbum.getPhotoAlbum(session);
                                
                    for (int j = 0; j < pa.getPhotoCount(); j++) {
                        out.write("<td>");
                        out.write("<a href='./DisplayPhotoServlet?photo=" + j + "'>");
                        out.write("<img src='./DisplayPhotoServlet?photo=" + j + "' alt='photo' height='120' width='150'> ");
                        out.write("</a>");
                        out.write("</td>");
                    }
                %>
            
                <td bgcolor='#cccccc' width='120' height='120'>
                <form align='left' action='UploadServlet' method='post' enctype='multipart/form-data'>
                <input value='Choose' name='myFile' type='file' accept='image/jpeg'><br>
                <input value='Upload' type='submit'><br>
                </form>
                </td>
                </tr>

                <tr>
                
             <%   
                for (int j = 0; j < pa.getPhotoCount(); j++) {
                    out.write("<td align='center'>");
                    out.write(pa.getPhotoName(j));
                    out.write("</td>");
                }
                    %>
                </tr>

                <tr>
            
            <%
                for (int j = 0; j < pa.getPhotoCount(); j++) {
                    out.write("<td align='center'>");
                    out.write("<a href='RemovePhotoServlet?photo=" + j + "'>remove</a>");
                    out.write("</td>");
                }
            %>
            </tr>
            </table>
            

    </body>
</html>
