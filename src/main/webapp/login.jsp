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
        fieldset{
            width: 50%;
        }
    </style>
</head>
<body>


<fieldset>
    <legend>Enter data to get into the system</legend>
    <form action="login">
        <table>
            <tr>
                <th>Login:</th>
                <th><input name="login" type="text"/></th>
            </tr>
            <tr>
                <th>Password:</th>
                <th><input name="password" type="password"/></th>
            </tr>
        </table>
        <br/>
        <input type="submit" value="login"/>
        <br/>
        <span style="color:red">${requestScope.error}</span>
    </form>
    <br/>
    <form action="register.jsp">
        <input type="submit" value="register"/>
    </form>
</fieldset>


</body>
</html>
