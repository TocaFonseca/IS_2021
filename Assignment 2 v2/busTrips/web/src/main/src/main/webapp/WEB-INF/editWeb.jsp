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
    <h1>Edit Profile</h1>
    <h3>Fill the configurations you want to change</h3>
    <form action="edit" method="post">
        <label for="name">Name:</label>
        <input id="name" name="name" size="30" /><br>

        <label for="email">Email:</label>
        <input id="email" name="email" size="30" /><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" size="30" /><br>

        <label for="address">Address:</label>
        <input id="address" name="address" size="30" /><br>

        <label for="birth">Date of Birth:</label>
        <input type="date" id="birth" name="birth"/><br>
        <br>${message}

        <br><br>
        <button type="submit">Change my details</button>
    </form>

    <br><a href="/web/home">Back to Main Menu</a>
    <br><a href="/web/logout">Logout</a>
</div>
</body>
</html>