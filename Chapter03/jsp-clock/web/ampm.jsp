
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>If page</title>
    </head>
    <body>
        <jsp:useBean id="myBean" class="javaeems.chapter3.clockbean.ConfigurableClockBean"/>
        <jsp:setProperty name="myBean" property="dateFormat" value="a"></jsp:setProperty>
        <c:if test="${myBean.readableDate=='AM'}">
            time for tea ! 
        </c:if>
        <c:if test="${myBean.readableDate=='PM'}">
            time for coffee ! 
        </c:if>  
    </body>
</html>
