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
    <h1>Manager ${user.name}'s Page</h1>

    <br><a href="/web/registerTrip">Create a trip</a><br> <!--Requirement 13.-->
    <br><a href="/web/topPassengers">Top Passenger's List</a><br> <!--Requirement 15.-->
    <br><a href="/web/searchDates">Search for bus trips</a><br> <!--Requirement 16.-->
    <br><a href="/web/dateTrips">Search Trips By Date</a><br> <!--Requirement 17.-->
    <br><a href="/web/logout">Logout</a><br> <!--Requirement 5.-->
</div>
</body>
</html>