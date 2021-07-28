<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <jsp:useBean id="weekBean" class="javaeems.chapter3.beans.MyWeek"/>
    <jsp:setProperty name="weekBean" property="name" value="Danny"></jsp:setProperty>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Week Example</title>
    </head>
    <body><div align='center'>
        
        <h2>${weekBean.description}</h2>
        There are of course <jsp:getProperty name="weekBean" property="numberDays"/> days in the week<p>
        <jsp:getProperty name="weekBean" property="name"/> works on
        <c:forEach var="day" items="${weekBean.workingDays}">
            ${day},
        </c:forEach>
            leaving ${weekBean.numberDays - weekBean.numberWorkingDays} days to enjoy other things in life.<p>
        <c:set var="numberDaysOff" scope="session" value="${weekBean.numberDays - weekBean.numberWorkingDays}"/>
        That means ${weekBean.name} is working ${100 * weekBean.numberWorkingDays / weekBean.numberDays}% of the time
        <p>
        <c:if test="${weekBean.numberWorkingDays > (weekBean.numberDays - weekBean.numberWorkingDays)}" >
            This is far from ideal :(
            <p>
        </c:if> 
        <c:if test="${weekBean.weekendOff && !((weekBean.numberDays - weekBean.numberWorkingDays) == 0)}" >
            But at least ${weekBean.name} gets some time off and it is at the weekend
        </c:if> 
    </div></body>  
</html>