<%-- 
    Document   : login
    Created on : Jun 13, 2014, 5:07:50 PM
    Author     : dannycoward
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="loginForm" method="POST" action="j_security_check">
                            <p><strong>Enter your user name: </strong>
                                <input type="text" name="j_username" size="15"/></p>
                            <p><strong>Enter your password: </strong>
                                <input type="password" size="15" name="j_password"/></p>
                            <p>
                                <input type="submit" value="Submit"/>
                                <input type="reset" value="Reset"/></p>
                        </form> 
    </body>
</html>
