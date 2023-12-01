<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Permissions</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1>Permissions</h1>
    <form action="controller?action=Decentralization" method="post">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Account ID</th>
                <th>Role ID</th>
                <th>Is Grant</th>
                <th>Note</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="grant" items="${listGrant}">
                <tr>
                    <td>${grant.accountId}</td>
                    <td>${grant.roleId}</td>
                    <td>${grant.isGrant}</td>
                    <td>${grant.note}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary" value="Decentralization">Decentralization</button>
    </form>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
