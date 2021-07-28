<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <jsp:useBean id="myWallet" class="javaeems.chapter3.beans.MyWallet"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>For each</title>
    </head>
    <body>
        Odd Numbers up to 20:
        <c:forEach var="i" begin="1" end="20" step="2">
            ${i}&nbsp;
        </c:forEach>
            <p/>
        Coins I have:
        <c:forEach var="coin" items="${myWallet.coins}">
            ${coin}&nbsp;
        </c:forEach> 
            <p/>
        Notes I have:    
        <c:forEach var="note" items="${myWallet.notes}">
            ${note}&nbsp;
        </c:forEach>
            <p/>
        Receipts:
        <c:forEach var="receipt" items="${myWallet.receipts}">
            ${receipt}&nbsp;
        </c:forEach>    
    </body>
</html>
