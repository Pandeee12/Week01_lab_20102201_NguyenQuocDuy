<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11/22/2023
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="container mt-5">
        <form action="control" method="get">
            <h1>Account Information</h1>
            <table class="table">
                <tr>
                    <th>Account ID</th>
                    <td>$(account.account_id}</td>
                </tr>
                <tr>
                    <th>FullName</th>
                    <td>$(account.fullname}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>$(account.email}</td>
                </tr>
                <tr>
                    <th>Phone</th>
                    <td>$(account.phone}</td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>$(account.status}</td>
                </tr>
            </table>
            <div class="mt-3">
                <button name="action" value="LogOut" type="submit" class="btn btn-danger">LogOut</button>
            </div>
        </form>

    </div>

</body>
</html>
