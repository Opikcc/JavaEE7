package javaeems.chapter3.clockbean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyDateTag extends SimpleTagSupport {
    private String dateFormat;
    
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(this.dateFormat);
        String today = sdf.format(now);        
        out.println(today);
    }
    
}