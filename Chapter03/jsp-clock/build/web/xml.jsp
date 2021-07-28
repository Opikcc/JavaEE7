<%@page language="java" contentType="text/html"%>
<html>
    <head>
        <title>Generate XML Element</title>
    </head>
    <body>
        <jsp:element name="my-element">
            <jsp:attribute name="my-attribute">
                Here is the value of my-attribute 
            </jsp:attribute>
            <jsp:body>
                Here is the body of my-element 
            </jsp:body>
        </jsp:element>
    </body>
</html>