<%@ page import="java.io.*" %><%@ page import="java.net.*" %><%@page contentType="image/gif" %><%OutputStream o = response.getOutputStream();
    InputStream is =
      (new URL("http://img2.timeinc.net/people/i/2008/database/madonna/madonna300.jpg")).openStream();
    byte[] buf = new byte[32 * 1024]; // 32k buffer
    int nRead = 0;
    while( (nRead=is.read(buf)) != -1 ) {
        o.write(buf, 0, nRead);
    }
    o.flush();
    o.close();// *important* to ensure no more jsp output
    return;
%>