<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 17.06.2021
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<h1>Welcome to the system ${user.firstname} ${user.lastname}</h1>
<h2 style="color: red">You are joined as manager</h2>
<h3>Information about you:</h3><br/>
<b>phone:</b> ${user.phone},<br/>
<b>email:</b> ${user.email},<br/>
<b>status:</b> ${user.status},<br/>
<b>login:</b> ${user.login} <br/><br/><br/>

<form action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="logout">
</form>

<br/><br/><br/>

<form action="${pageContext.request.contextPath}/main.jsp">
    <input type="submit" value="main">
</form>
</body>
</html>
