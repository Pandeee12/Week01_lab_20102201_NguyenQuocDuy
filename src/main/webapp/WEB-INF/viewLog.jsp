<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11/22/2023
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Log</title>
</head>
<body>
    <h1>View Log</h1>
    <table>
        <tr>
            <th>Account ID</th>
            <th>Login Time</th>
            <th>Logout Time</th>
        </tr>
        <c:forEach var="log" items="${LogList}">
            <tr>
                <td>${log.accountId}</td>
                <td>${log.loginTime}</td>
                <td>${log.logoutTime}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
