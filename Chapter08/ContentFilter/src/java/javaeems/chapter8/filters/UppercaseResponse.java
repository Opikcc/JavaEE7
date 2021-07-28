package javaeems.chapter8.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


public class UppercaseResponse extends HttpServletResponseWrapper {
    
    UppercaseResponse(HttpServletResponse response) {
        super(response);
    }
    
    @Override
    public PrintWriter getWriter() throws IOException {
        return new UppercaseWriter(super.getWriter());
    }
    
    class UppercaseWriter extends PrintWriter {
    
        public UppercaseWriter(PrintWriter w) {
            super(w);
        }
    
        @Override
        public void write(char[] cbuf, int off, int len)  {
            char[] charsToConvert = Arrays.copyOfRange(cbuf, off, off+len);
            String convertedString = (new String(charsToConvert)).toUpperCase();
            super.write(convertedString.toCharArray(), 0, len);
        }
    
    }
  
    
}


