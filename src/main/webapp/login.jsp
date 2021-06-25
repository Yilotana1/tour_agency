<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 17.06.2021
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <style>

        <jsp:include page="styles/style.css"/>

        th{
            border: 0;
        }
        fieldset{
            width: 50%;
        }
    </style>
</head>
<body>

<jsp:include page="html/locale_buttons.html"/>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="message"/>

<fmt:message key="login_button" var="login_button"/>
<fmt:message key="register_button" var="register_button"/>
<fmt:message key="main" var="main"/>
<fmt:message key="login_label" var="login_label"/>
<fmt:message key="password_label" var="password_label"/>

<fieldset>
    <legend><fmt:message key="enter_data_to_login"/></legend>
    <form action="login">
        <table>
            <tr>
                <th>${login_label}</th>
                <th><input name="login" type="text"/></th>
            </tr>
            <tr>
                <th>${password_label}</th>
                <th><input name="password" type="password"/></th>
            </tr>
        </table>
        <br/>
        <input type="submit" value="${login_button}"/>
        <br/>
        <span style="color:red">${requestScope.error}</span>
    </form>
    <br/>
    <form action="register.jsp">
        <input type="submit" value="${register_button}"/>
    </form>

    <form action="${pageContext.request.contextPath}/">
        <input type="submit" value="${main}">
    </form>
</fieldset>


</body>
</html>
