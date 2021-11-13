<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bus trips Website</title>
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
    <h1>Register a New User</h1>
    <form action="register" method="post">
        <label for="name">Name:</label>
        <input name="name" size="30" />
        <br><br>
        <label for="email">Email:</label>
        <input name="email" size="30" />
        <br><br>
        <label for="date">Birthday:</label>
        <input type="date" id="date" name="date" size="30" />
        <br><br>
        <label for="address">Address:</label>
        <input name="address" size="30" />
        <br><br>
        <label for="password">Password:</label>
        <input type="password" name="password" size="30" />
        <br><br>
        <button type="submit">Register</button>
    </form>
</div>
</body>
<script type="text/javascript">

    $(document).ready(function() {
        $("#loginForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },

                password: "required",
            },

            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter a valid email address"
                },

                password: "Please enter password"
            }
        });

    });
</script>
</html>