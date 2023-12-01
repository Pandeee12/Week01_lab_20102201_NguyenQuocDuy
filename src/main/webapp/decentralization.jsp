<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Permissions</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            max-width: 300px;
            margin: 0 auto;
            padding: 15px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-button {
            display: block;
            width: 100%;
            padding: 10px;
            margin-top: 40px;
            background: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-button:hover {
            background: #0056b3;
        }
    </style>

</head>
<body>
<div class="container mt-5">
    <form action="controller?action=UpdateGrant" method="post">
        <label>Account:</label>
        <select id="selectedAcc" name="selectedAcc">
            <c:forEach var="account" items="${listAccounts}">
                <option value="${account.accountId}">${account.accountId}</option>
            </c:forEach>
        </select>
        <br>
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
