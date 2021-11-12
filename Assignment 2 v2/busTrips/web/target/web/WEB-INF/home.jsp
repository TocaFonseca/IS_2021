<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Admin CPanel - Bookshop</title>
</head>
<body>
<div style="text-align: center">
  <h1>Welcome BusTrips!</h1>
  <b>${user.name} (${user.id})</b>
  <h3>Your wallet:</h3>
  <b>${user.wallet}</b>

  <br><a href="logout">Logout</a>
</div>
</body>
</html>