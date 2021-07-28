package javaeems.chapter4.photo;

import java.util.*;
import java.io.*;
import javax.inject.*;
import javax.enterprise.context.*;
import javax.servlet.ServletContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "photoAlbum")
@ApplicationScoped
public class PhotoAlbum implements Serializable { 
    
    private List<Photo> photos = new ArrayList<>();
    private Photo currentPhoto = null;
    
       
    public PhotoAlbum() {
    }
    
    public int getViewerCount() {
        ServletContext servletContext = (ServletContext) FacesContext
    .getCurrentInstance().getExternalContext().getContext();
        List sessions = (List) servletContext.getAttribute(UserCounter.SERVLET_CONTEXT_SESSION_LIST);
        if (sessions != null) {
            return sessions.size();
        } else {
            return 0;
        }
    }
    
    public void setCurrentPhoto(Photo p) {
        this.currentPhoto = p;
    }
    
    public Photo getCurrentPhoto() {
        return this.currentPhoto;
    }
    
    public void addPhoto(Photo p) {
        if (this.containsId(p.getId())) {
            this.removePhoto(this.getPhotoById(p.getId()));
        }
        this.photos.add(p);
    }
    
    private Photo getPhotoById(long id) {
        for (Photo photo : this.photos) {
            if (photo.getId() == id) {
                return photo;
            }
        }
        return null;
    }
    
    public boolean containsId(long id) {
        return this.getPhotoById(id) != null;
    }
    
    public List<Photo> getPhotos() {
        return this.photos;
    }
    
    public void removePhoto(Photo photo) {
        this.photos.remove(photo);
    }
    
    public Photo getPhoto(long id) {
        for (Photo photo : this.photos) {
            if (photo.getId() == id) {
                return photo;
            }
        } 
        return null;
    }
   
    public List getPhotoNames() {
        List<String> names = new ArrayList<>();
        for (Photo photo: this.photos) {
            names.add(photo.getName());
        }
        return names;
    }   

    
    public List getPhotoFilenames() {
        List<String> filenames = new ArrayList<>();
        for (Photo photo: this.photos) {
            filenames.add(photo.getFilename());
        }
        return filenames;
    }
    
    public int getIndexOf(String photoName) {
        for (Photo photo : this.photos) {
            if (photo.getFilename().equals(photoName)) {
                return photos.indexOf(photo);
            }
        }
        return -1;
    }
    
    
    public byte[] getPhotoData(int i) {
        Photo photo = this.photos.get(i);
        return photo.getData();
    }
    
    public byte[] getPhotoDataByName(String name) {
        for (Photo photo : this.photos) {
            if (photo.getFilename().equals(name)) {
                return photo.getData();
            }
        }
        return null;
    }
    
    public String getPhotoName(int i) {
        Photo photo = this.photos.get(i);
        return photo.getFilename();
    }
    
    public int getPhotoCount() {
        return photos.size();
    }
    


    
}
