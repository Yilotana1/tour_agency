<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.touragency.model.entity.enums.UserStatus" %>
<%@ page import="com.example.touragency.model.entity.enums.Role" %>
<%@ page import="com.example.touragency.controller.commands.Paginator" %>
<%--
  Created by IntelliJ IDEA.
  User: tolik
  Date: 19.06.2021
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<h1>Users</h1>

<br/>

<form>
    <input type="text" name="search"/>
    <input type="submit" value="search"/>
</form>

<br/>

<b>Show first:</b>
<br/>
<br/>
<table>
    <tr>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <c:if test="${requestScope.order.equals('clients')}">
                    <input style="color: red" type="submit" value="clients"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('clients')}">
                    <input type="submit" value="clients"/>
                </c:if>
                <input type="hidden" value="clients" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <c:if test="${requestScope.order.equals('managers')}">
                    <input style="color: red" type="submit" value="manager"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('managers')}">
                    <input type="submit" value="managers"/>
                </c:if>
                <input type="hidden" value="managers" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <c:if test="${requestScope.order.equals('non_blocked')}">
                    <input style="color: red" type="submit" value="non_blocked"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('non_blocked')}">
                    <input type="submit" value="non_blocked"/>
                </c:if>
                <input type="hidden" value="non_blocked" name="order"/>
            </form>
        </td>
        <td style="border:none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <c:if test="${requestScope.order.equals('blocked')}">
                    <input style="color: red" type="submit" value="blocked"/>
                </c:if>
                <c:if test="${!requestScope.order.equals('blocked')}">
                    <input type="submit" value="blocked"/>
                </c:if>
                <input type="hidden" value="blocked" name="order"/>
            </form>
        </td>
    </tr>
</table>

<br/>
<br/>

<table>
    <tr>
        <th style="color: red"><b>FIRSTNAME</b></th>
        <th style="color: red"><b>LASTNAME</b></th>
        <th style="color: red"><b>PHONE</b></th>
        <th style="color: red"><b>LOGIN</b></th>
        <th style="color: red"><b>STATUS</b></th>
        <th style="color: red"><b>EMAIL</b></th>
        <th style="color: red"><b>ROLE</b></th>
    </tr>
    <c:forEach var="client" items="${requestScope.items}">
        <form action="${pageContext.request.contextPath}/admin/manage_users">
            <input type="hidden" name="id" value="${client.id}"/>
            <tr>
                <th>${client.firstname}</th>
                <th>${client.lastname}</th>
                <th>${client.phone}</th>
                <th>${client.login}</th>
                <th>

                    <c:if test="${!client.role.equals(Role.ADMIN)}">

                        <select name="status">

                            <c:if test="${client.status.equals(UserStatus.NON_BLOCKED)}">
                                <option selected="selected"
                                        value="${UserStatus.NON_BLOCKED.id}">${UserStatus.NON_BLOCKED}</option>
                                <option value="${UserStatus.BLOCKED.id}">${UserStatus.BLOCKED}</option>
                            </c:if>

                            <c:if test="${client.status.equals(UserStatus.BLOCKED)}">
                                <option selected="selected"
                                        value="${UserStatus.BLOCKED.id}">${UserStatus.BLOCKED}</option>
                                <option value="${UserStatus.NON_BLOCKED.id}">${UserStatus.NON_BLOCKED}</option>
                            </c:if>

                        </select>

                    </c:if>

                    <c:if test="${client.role.equals(Role.ADMIN)}">
                        ${client.status}
                    </c:if>
                </th>
                <th>${client.email}</th>


                <th>
                    <c:if test="${!client.role.equals(Role.ADMIN)}">

                        <select name="role">

                            <c:if test="${client.role.equals(Role.CLIENT)}">
                                <option selected="selected" value="${Role.CLIENT.id}">${Role.CLIENT}</option>
                                <option value="${Role.MANAGER.id}">${Role.MANAGER}</option>
                            </c:if>
                            <c:if test="${client.role.equals(Role.MANAGER)}">
                                <option selected="selected" value="${Role.MANAGER.id}">${Role.MANAGER}</option>
                                <option value="${Role.CLIENT.id}">${Role.CLIENT}</option>
                            </c:if>

                        </select>

                    </c:if>

                    <c:if test="${client.role.equals(Role.ADMIN)}">
                        ${client.role}
                    </c:if>
                </th>
                <c:if test="${client.role.equals(Role.ADMIN)}">
                    <th></th>
                </c:if>
                <c:if test="${!client.role.equals(Role.ADMIN)}">
                    <th>
                        <input type="hidden" name="order" value="${requestScope.order}">
                        <input type="hidden" name="${Paginator.PAGE}" value="${requestScope.page}">
                        <input type="submit" value="update"/>
                    </th>
                </c:if>
            </tr>
        </form>

    </c:forEach>
</table>

<br/>

<table>
    <tr>
        <th style="border: none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <input type="hidden" name="${Paginator.PREVIOUS_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <input type="submit" value="previous">
            </form>
        </th>
        <c:forEach begin="1" end="${requestScope.page_count}" step="1" var="i">
            <th style="border: none">
                <form action="${pageContext.request.contextPath}/admin/manage_users">
                    <input type="hidden" name="${Paginator.PAGE}" value="${i}"/>
                    <input type="hidden" name="order" value="${requestScope.order}"/>
                    <c:if test="${requestScope.page.equals(i)}">
                        <input style="color: red" type="submit" value="${i}">
                    </c:if>

                    <c:if test="${!requestScope.page.equals(i)}">
                        <input type="submit" value="${i}">
                    </c:if>

                </form>
            </th>
        </c:forEach>
        <th style="border: none">
            <form action="${pageContext.request.contextPath}/admin/manage_users">
                <input type="hidden" name="${Paginator.NEXT_PAGE}" value="${requestScope.page}"/>
                <input type="hidden" name="order" value="${requestScope.order}"/>
                <input type="submit" value="next">
            </form>
        </th>
    </tr>
</table>

<br/>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/main.jsp">
    <input type="submit" value="main">
</form>

<form action="${pageContext.request.contextPath}/admin/admin_page.jsp">
    <input type="submit" value="profile">
</form>

</body>
<head>
    <title>Title</title>

    <style>

        th {
            border: 1px solid black;
        }

        td {
            border: 10px groove black;
        }
    </style>
</head>
</html>
