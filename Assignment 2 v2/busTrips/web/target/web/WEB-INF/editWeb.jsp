<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js">
    </script>
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

        <br><br>
        <button type="submit">Change my details</button>
    </form>

    <br><a href="/web/home">Back to Main Menu</a>
    <br><a href="/web/logout">Logout</a>
</div>
</body>
</html>