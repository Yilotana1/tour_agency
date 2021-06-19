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
    <title>Register</title>
</head>
<body>
<fieldset>
    <legend>We need some information about you</legend>
    <form action="register">
            <table>
                <tr>
                    <th>Firstname:</th>
                    <th><input type="text" name="firstname"/></th>
                </tr>
                <tr>
                    <th>Lastname:</th>
                    <th><input type="text" name="lastname"/></th>
                </tr>
                <tr>
                    <th>Phone:</th>
                    <th><input type="text" name="phone"/></th>
                </tr>
                <tr>
                    <th>Email:</th>
                    <th><input type="text" name="email"/></th>
                </tr>
                <tr>
                    <th>Login:</th>
                    <th><input type="text" name="login"/></th>
                </tr>
                <tr>
                    <th>Password:</th>
                    <th><input type="password" name="password"/></th>
                </tr>
            </table>
            <br/>

            <input type="submit" value="register"/>
            <br/>
            <span style="color:red">${requestScope.error}</span>

    </form>
    <form action="main.jsp">
        <input type="submit" value="main"/>
    </form>
</fieldset>

</body>
</html>
