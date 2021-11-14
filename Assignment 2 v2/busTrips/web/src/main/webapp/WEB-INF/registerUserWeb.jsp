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
    <h1>Register a New User</h1>
    <form action="register" method="post">
        <label for="name">Name:</label>
        <input id="name" name="name" size="30" />
        <br><br>
        <label for="email">Email:</label>
        <input id="email" name="email" size="30" />
        <br><br>
        <label for="date">Birthday:</label>
        <input type="date" id="date" name="date" size="30" />
        <br><br>
        <label for="address">Address:</label>
        <input id="address" name="address" size="30" />
        <br><br>
        <label for="password">Password:</label>
        <input id="password" type="password" name="password" size="30" />
        <br><br>
        <button type="submit">Register</button>
    </form>
</div>
</body>
</html>