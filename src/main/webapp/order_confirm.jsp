<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 24.06.2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 style="color: red">${requestScope.message}</h2>

${requestScope.errorMessageTour}<br/>
${requestScope.errorMessageUser}

<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="main">
</form>
</body>
</html>
