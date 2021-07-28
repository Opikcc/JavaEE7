
package javaeems.chapter4.photo;

import java.io.*;
import java.util.Date;


public class Photo implements Serializable {
    private long id;
    private byte[] data;
    private String filename;
    private Date dateTaken;
    private String name;
    private boolean isPublic;
    
    public Photo(long id, byte[] data, String filename, String name, Date date, boolean isPublic) {
        this.id = (id==-1) ? System.currentTimeMillis() : id;
        this.data = data;
        this.name = name;
        this.filename = filename;
        this.dateTaken = date;
        this.isPublic = isPublic;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Date getDateTaken() {
        return this.dateTaken;
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public String getViewUri() {
        return "DisplayPhotoServlet?photoid=" + this.id;
    }
    
    public boolean isPublic() {
        return this.isPublic;
    }
    
    public String toString() {
        return "Photo: " + this.name;
    }
    
    
}
