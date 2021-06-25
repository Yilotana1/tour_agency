<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 19.06.2021
  Time: 02:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <style>
        <jsp:include page="styles/style.css"/>
        fieldset{
            width: 50%;
        }
    </style>
</head>
<body>
<jsp:include page="html/locale_buttons.html"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>
<fieldset>
    <legend><fmt:message key="we_need_inf_about_you"/></legend>
    <form action="register">
            <table>
                <tr>
                    <th><fmt:message key="firstname_1"/></th>
                    <th><input type="text" name="firstname"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="lastname_1"/></th>
                    <th><input type="text" name="lastname"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="phone_1"/></th>
                    <th><input type="text" name="phone"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="email_1"/></th>
                    <th><input type="text" name="email"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="login_label"/></th>
                    <th><input type="text" name="login"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="password_label"/>:</th>
                    <th><input type="password" name="password"/></th>
                </tr>
            </table>
            <br/>
            <fmt:message key="reg" var="reg"/>
            <input type="submit" value="${reg}"/>
            <br/>
            <span style="color:red">${requestScope.error}</span>

    </form>
    <fmt:message key="main" var="main"/>
    <form action="${pageContext.request.contextPath}/">
        <input type="submit" value="${main}">
    </form>
</fieldset>

</body>
</html>
