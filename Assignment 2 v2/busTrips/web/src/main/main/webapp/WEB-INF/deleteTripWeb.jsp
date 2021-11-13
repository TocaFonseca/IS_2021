<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Trip</title>
</head>
<body>
<div style="text-align: center">
    <h2>Delete Trip</h2>
    <h3>Select a trip to be deleted</h3>

    <form action="deleteTrip" method="post">

        <c:forEach var="trip" items="${tripsList}">
            <input type="radio" id="ticket" name="ticket" value="${trip.id}">
            <label for="ticket">
                <c:out value="${trip.departure}"/> -> <c:out value="${trip.destination}"/>
            </label><br>
        </c:forEach>

        <br>${message}<br>

        <button type="submit">Delete Trip</button>
    </form>

    <br><a href="/web/homeMng">Back to Main Menu</a>
    <br><a href="/web/logoutMng">Logout</a>
</div>
</body>

</html>