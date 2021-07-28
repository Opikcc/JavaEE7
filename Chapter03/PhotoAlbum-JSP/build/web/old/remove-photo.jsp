<%@page %>
<%

        String indexString = request.getParameter("photo");        
        int index = (new Integer(indexString.trim())).intValue();
        javaeems.chapter2.photos.advanced.PhotoAlbum myPhotos = javaeems.chapter2.photos.advanced.PhotoAlbum.getPhotoAlbum(session);
        myPhotos.removePhoto(index);


%>
<jsp:forward page="./display-album-iterator.jsp"/>
