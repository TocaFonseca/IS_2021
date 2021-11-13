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

    <h1>Manager ${user.name} (${user.id})'s page</h1>
    <br><a href="/web/deleteTrip">Delete future bus trips</a><br> <!--Requirement 14.-->
    <br><a href="/web/searchDates">Search for bus trips</a><br> <!--Requirement 16.-->
    <br><a href="/web/logout">Logout</a><br> <!--Requirement 5.-->

</div>
</body>
</html>