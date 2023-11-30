<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/10/2023
  Time: 7:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <div class="container mt-5">
        <h1>Account Information</h1>
        <form action="control" method="get">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Account ID</th>
                        <th>FullName</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Status</th>
                        <th>Action</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="account" item="$(dashboard)">
                        <tr>
                            <td>${account.account_id}</td>
                            <td>${account.fullname}</td>
                            <td>${account.email}</td>
                            <td>${account.phone}</td>
                            <td>${account.status}</td>
                            <td>
                                <input type="hidden" name="account_id_delete" value="${account.account_id}">
                                <button type="submit" class="btn btn-danger" name="action" value="Delete">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="mt-3">
                <a href="create_account.jsp" class = "btn btn-success">Create</a>
                <button name="action" value="ViewRole" type="submit" class="btn btn-success">ViewRole</button>
                <button name="action" value="ViewLog" type="submit" class="btn btn-success">ViewLog</button>
            </div><br>
            <div class="mt-3">
                <button name="action" value="LogOut" type="submit" class="btn btn-success">LogOut</button>
            </div>
        </form>
    </div>
</body>
</html>
