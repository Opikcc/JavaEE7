package javaeems.chapter2.photos.basic;

import java.util.*;
import javax.servlet.ServletContext;

public class PhotoAlbum {
    public static String ATTRIBUTE_NAME = "Photo_Album";
    private List<byte[]> photoDataList = new ArrayList<byte[]> ();
    private List<String> names = new ArrayList<String>();
    
    
    public static PhotoAlbum getPhotoAlbum(ServletContext servletContext) {
        if (servletContext.getAttribute(ATTRIBUTE_NAME) == null) {
            PhotoAlbum pa = new PhotoAlbum();
            servletContext.setAttribute(ATTRIBUTE_NAME, pa);
        }
        return (PhotoAlbum) servletContext.getAttribute(ATTRIBUTE_NAME);
    }
    
    public synchronized void addPhoto(String name, byte[] bytes) {
        this.photoDataList.add(bytes);
        this.names.add(name);
    }
    
    public synchronized byte[] getPhotoData(int i) {
        return (byte[]) photoDataList.get(i);
    }
    
    public synchronized String getPhotoName(int i) {
        return (String) names.get(i);
    }
    
    public synchronized int getPhotoCount() {
        return photoDataList.size();
    }
    
    public synchronized void removePhoto(int i) {
        photoDataList.remove(i);
        names.remove(i);
    }
    
    
    
}
