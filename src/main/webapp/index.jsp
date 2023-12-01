<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<style>
    * {
        box-sizing: border-box;
    }

    body {

        margin: 0;
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .wrapper {
        width: 500px;
        padding: 20px;
        border: 1px solid rgb(128, 128, 128);

    }

</style>
<div class="wrapper">
    <form action="controller" method="POST">
        <div class="mb-3">
            <label for="account_id" class="form-label">AccountID</label>
            <input id="account_id" name="account_id" class="form-control" aria-describedby="emailHelp">

        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input id="password" name="password" type="password" class="form-control">
        </div>

        <button name="action" value="login" type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
</body>
</html>
