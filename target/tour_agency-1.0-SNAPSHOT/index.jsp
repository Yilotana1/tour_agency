<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.touragency.Calculator" %>

<%!
    int sum(int a, int b){
        return a + b;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="hello">
    Enter tours country : <input type="text" name="country"/>
    <br/><br/>
    <input type="submit" value="enter">

</form>
</body>
</html>
