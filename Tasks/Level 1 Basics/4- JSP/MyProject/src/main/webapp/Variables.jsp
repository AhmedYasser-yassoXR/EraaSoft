<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    //This is JSP Declaration
    // Define function
    public String getInfo(int id, String name) {
        return id + " " + name;
    }
%>

<%
    // Define variables
    int id = 1;
    String name = "Ahmed";

    // Call function
    String result = getInfo(id, name);
%>
    
    
<!DOCTYPE html>
<html>
<head>
    <title>My First JSP</title>
</head>
<body>
    <h1>JSP variable example</h1>
</body>
</html>
