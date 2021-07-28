<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Clock</title>
    </head>
    <body>
        <jsp:useBean id="myBean" class="javaeems.chapter3.clockbean.ClockBean"/>
        <div align='center'>
            <br/>
            Hello there !
            <br/><br/>
            It's been 
                <jsp:getProperty name="myBean" property="currentTimeSinceEpoch"/> 
            milliseconds since midnight, January 1st 1970 
            <br/><br/>
            In other words, its <jsp:getProperty name="myBean" property="readableDate"/>
        </div>
    </body>
</html>
