<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
</head>
<body>
<div style="text-align: center">
    <h1>Manager ${user.name} (${user.id}) page</h1>
    <h3>Your wallet:</h3>
    <b>${user.wallet}</b>

    <br><a href="/web/logout">Logout</a><br> <!--Requirement 5.-->
</div>
</body>
</html>