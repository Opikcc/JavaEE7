package javaeems.chapter3.clockbean;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class MyTimeSinceEpochTag extends SimpleTagSupport { 
    
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print(System.currentTimeMillis());   
    }
       
}
