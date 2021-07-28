<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Clock</title>
    </head>
    <body>
        <div align='center'>
            <br/>
            Hello there !
            <br/><br/>
            It's been <%=System.currentTimeMillis()%> milliseconds
            since midnight, January 1st 1970 
            <br/><br/>
            In other words, its 
            <% 
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE");
                String today = sdf.format(now);
                out.println(today.trim());
            %>
        </div>
    </body>
</html>
