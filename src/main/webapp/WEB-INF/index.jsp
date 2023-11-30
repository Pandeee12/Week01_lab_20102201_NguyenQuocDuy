<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="control" method="get">

        <div class = "mb-3">
            <label for="account_id" class ="form-label">Account_ID</label>
            <input id="account_id" name ="account_id" class="form-control" >
        </div>
        <div class="mb-3">
            <label for="password" class="form-label" >Password</label>
            <input id="password" name="password" type="password" class="form-control">
        </div>

        <button name="action" value="login" type="submit" class="btn btn-primary">Login</button>

    </form>

</body>
</html>