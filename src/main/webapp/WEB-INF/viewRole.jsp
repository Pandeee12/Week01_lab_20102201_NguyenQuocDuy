<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11/22/2023
  Time: 10:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="container mt-5">
        <form action="control?action=RoleI" method="get">
            <label>Role:</label>
            <select id="selectedRole" name="selectedRole">
                <c:forEach var="role" items="${listRoles}">
                    <option value="${role.roleId}">${role.roleId}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary form-button">Submit</button>
        </form>
    </div>
</body>
</html>
