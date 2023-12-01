<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin tài khoản</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .table {
            max-width: 400px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <form action="controller" method="post">
        <h1>Thông tin tài khoản</h1>
        <table class="table">
            <tr>
                <th>Account ID</th>
                <td>${ACCOUNT.accountId}</td>
            </tr>
            <tr>
                <th>Full Name</th>
                <td>${ACCOUNT.fullName}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${ACCOUNT.email}</td>
            </tr>
            <tr>
                <th>Phone</th>
                <td>${ACCOUNT.phone}</td>
            </tr>
            <tr>
                <th>Status</th>
                <td>${ACCOUNT.status}</td>
            </tr>
        </table>
        <div class="mt-3">
            <button name="action" value="LogOut" type="submit" class="btn btn-success">LogOut</button>
        </div>
    </form>
</div>


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
