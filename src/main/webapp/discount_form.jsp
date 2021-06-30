<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 27.06.2021
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .discount_form {
            border: 0;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="message"/>

<fieldset style="width: 20%">
    <legend><fmt:message key="discount_settings"/></legend>
    <form action="${pageContext.request.contextPath}/manager/edit_discount">

        <table>

            <tr>
                <th class="discount_form"><fmt:message key="percent_step" />:</th>
                <th class="discount_form"><input name="percentStep" value="${requestScope.percentStep}"/></th>
            </tr>

            <tr>
                <th class="discount_form"><fmt:message key="max_percent" />:</th>
                <th class="discount_form"><input name="maxPercent" value="${requestScope.maxPercent}"/></th>
            </tr>

        </table>
        <br/>
        <fmt:message key="edit" var="edit"/>
        <input type="submit" value="${edit}"/>
    </form>

</fieldset>

</body>
</html>
