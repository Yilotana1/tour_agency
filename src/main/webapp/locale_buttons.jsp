<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 28.06.2021
  Time: 00:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<table align="right" class="locale_table">

<c:if test="${(requestScope.path == null)}">

    <tr>
        <th class="locale_table">
            <form>
                <input value="ua" type="submit"/>
                <input value="ua" type="hidden" name="locale"/>
            </form>
        </th>
        <th class="locale_table">
            <form>
                <input value="en" type="submit"/>
                <input value="en" type="hidden" name="locale"/>
            </form>
        </th>
    </tr>
</c:if>

    <c:if test="${!(requestScope.path == null)}">
        <tr>
            <th class="locale_table">
                <form name="${pageContext.request.contextPath}" action="${requestScope.path}">
                    <input value="ua" type="submit"/>
                    <input value="ua" type="hidden" name="locale"/>
                </form>
            </th>
            <th class="locale_table">
                <form name="${pageContext.request.contextPath}" action="${requestScope.path}">
                    <input value="en" type="submit"/>
                    <input value="en" type="hidden" name="locale"/>
                </form>
            </th>
        </tr>
    </c:if>


</table>

</body>
</html>
