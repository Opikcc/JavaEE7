<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="custom" uri="WEB-INF/my_custom_tags.tld"%>
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
            It's been <custom:time-since-epoch/> milliseconds
            since midnight, January 1st 1970 
            <br/><br/>
            In other words, its 
            <custom:date dateFormat="h:mm a, zzzz"/>
        </div>
    </body>
</html>
