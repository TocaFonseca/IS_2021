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
  <h1>Welcome BusTrips!</h1>
  <b>${user.name} (${user.id})</b>

  <br><a href="/web/edit">Edit My Profile</a><br> <!--Requirement 6.-->
  <br><a href="/web/availableDates">Available Trips</a><br> <!--Requirement 8.-->
  <br><a href="/web/chargeWallet">Charge My Wallet</a><br> <!--Requirement 9.-->
  <br><a href="/web/selectPurchase">Purchase Ticket</a><br> <!--Requirement 10.-->
  <br><a href="/web/selectRefund">Refund Ticket</a><br> <!--Requirement 10.-->
  <br><a href="/web/userTrips">User Trips</a><br> <!--Requirement 12.-->
  <br><a href="/web/logout">Logout</a><br> <!--Requirement 5.-->
  <br><a href="/web/deleteProfile">Delete my profile</a><br> <!--Requirement 7.-->

</div>
</body>
</html>