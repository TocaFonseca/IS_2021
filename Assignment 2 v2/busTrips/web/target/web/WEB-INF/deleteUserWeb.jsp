<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bus trips Website</title>
</head>
<body>
<div style="text-align: center">
    <h1>Delete Profile</h1>
    <form action="deleteProfile" method="post">
        <label for="password">Password:</label>
        <input id="password" type="password" name="password" size="30" />
        <br><br>
        <button type="submit">Permanently Delete</button>
        <br><a href="/web/home">Back to Main Menu</a>
        <br><a href="/web/WEB-INF/logout">Logout</a>
    </form>
</div>
</body>
</html>