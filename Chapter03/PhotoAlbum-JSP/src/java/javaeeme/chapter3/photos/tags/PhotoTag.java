package javaeeme.chapter3.photos.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PhotoTag extends SimpleTagSupport {
    private int index;
    private int width;
    private int height;
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<a href='photo.jsp?photo="+index+"'>");
        out.println("<img src='photo.jsp?photo="+index+"' alt='photo' height='"+this.height+"' width='"+this.width+"'>");
        out.println("</a>");
    }
    
    
    
    

    
}
