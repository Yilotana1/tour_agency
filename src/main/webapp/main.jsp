<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.touragency.model.entity.enums.Role" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<c:if test = "${sessionScope.role.equals(Role.CLIENT)}">
    <form action="client/client_page.jsp">
        <input type="submit" value="profile"/>
    </form>
</c:if>

<c:if test = "${sessionScope.role.equals(Role.ADMIN)}">
    <form action="admin/admin_page.jsp">
        <input type="submit" value="profile"/>
    </form>
</c:if>

<c:if test = "${sessionScope.role.equals(Role.MANAGER)}">
    <form action="manager/manager_page.jsp">
        <input type="submit" value="profile"/>
    </form>
</c:if>

<c:if test = "${sessionScope.role.equals(Role.UNKNOWN)}">
    <form action="login.jsp">
        <input type="submit" value="login"/>
    </form>

    <br/>

    <form action="register.jsp">
        <input type="submit" value="register"/>
    </form>
</c:if>
<br/>

</body>
</html>
