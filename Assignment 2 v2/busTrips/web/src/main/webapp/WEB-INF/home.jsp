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
  <h1>Welcome BusTrips!</h1>
  <b>${user.name} (${user.id})</b>
  <h3>Your wallet:</h3>
  <b>${user.wallet}</b>

  <br><a href="/web/edit">Edit My Profile</a><br> <!--Requirement 6.-->
  <br><a href="/web/availableDates">Available Trips</a><br> <!--Requirement 8.-->
  <br><a href="/web/chargeWallet">Charge My Wallet</a><br> <!--Requirement 9.-->
  <br><a href="/web/selectPurchase">Puchase Ticket</a><br> <!--Requirement 10.-->
  <br><a href="/web/userTrips">User Trips</a><br> <!--Requirement 12.-->
  <br><a href="/web/deleteProfile">Delete my profile</a><br> <!--Requirement 7.-->
  <br><a href="/web/logout">Logout</a><br> <!--Requirement 5.-->
</div>
</body>
</html>