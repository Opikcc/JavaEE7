package javaeems.chapter4.photo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;


@Named(value = "editPhotoBean")
@javax.enterprise.context.SessionScoped
public class EditPhoto implements Serializable {
    @Inject
    PhotoAlbum photoAlbum;
    private long id = -1;
    private boolean isPublic = false;
    private String filename;
    private String photoName;
    private Date dateTaken = new Date();
    private byte[] photoData = null;

    public EditPhoto() {}
    
    public boolean isNew() {
        return this.id == -1;
    }
    
    public boolean isPublic() {
        return isPublic;
    }
    
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }
    
    public String getPhotoName() {
        return this.photoName;
    }
    
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
    
    public String getFilename() {
        return this.filename;
    }

    public void reset() {
        this.id = -1;
        this.dateTaken = new Date();
        this.filename = null;
        this.photoData = null;
        this.photoName = null;
    }
    
    public void commit() {
        this.photoAlbum.addPhoto(this.getPhoto());
        this.reset();
    }
    
    private void collectData(Part uploadedPart) {
        if (uploadedPart.getSize() == 0) {return;} // dannyc
        try {
            ByteArrayOutputStream baos;
            try (InputStream is = uploadedPart.getInputStream()) {
                baos = new ByteArrayOutputStream();
                int i = 0;
                while ( (i=is.read()) != -1) {
                    baos.write(i);
                } 
                photoData =  baos.toByteArray();
            }
            baos.close();  
            } catch (Exception e) {
                photoData = null;
                throw new RuntimeException(e.getMessage()); // dannyc
            }
        this.filename = uploadedPart.getSubmittedFileName();
        this.photoName = this.filename.substring(0, this.filename.indexOf("."));
    }
    
    public byte[] getPhotoData() {
        return this.photoData; 
    }
    
    public void setUploadedPart(Part p) {
        this.collectData(p);
    }
    
    public Part getUploadedPart() {
        return null;
    }

    
    public String getPreviewUrl() {
        if (this.hasPhoto()) {
            return "DisplayPhotoServlet";
        } else {
            return "nopreview.png";
        } 
    }
    
    public void setPhoto(Photo p) {
        this.id = p.getId();
        this.isPublic = p.isPublic();
        this.filename = p.getFilename();
        this.photoName = p.getName();
        this.dateTaken = p.getDateTaken();
        this.photoData = p.getData();
    }
    
    public boolean hasPhoto() {
        return (this.photoData != null);
    }
    
    public Photo getPhoto() {
        if (this.hasPhoto()) {
            Photo p = new Photo(this.id, this.getPhotoData(), this.getFilename(), this.photoName, this.dateTaken, this.isPublic);
            return p;
        } else {
            return null;
        }
    }
    
}
