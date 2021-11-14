<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bus trips Website</title>
</head>
<body>
<div style="text-align: center">
    <h2>Available Trips List</h2>
    <c:forEach var="trip" items="${tripsList}">
        <div>
            <b><c:out value="${trip.departure}"/>, <c:out value="${trip.depDate}"/> -> <c:out value="${trip.destination}"/> , <c:out value="${trip.destDate}"/></b><br>
            <c:forEach var="trip_user" items="${trip.users}">
                <c:out value="${trip_user.name}"/> (<c:out value="${trip_user.id}"/>)<br>
            </c:forEach>
        </div>
        <p>
    </c:forEach>

    <br><a href="/web/dateTrips">Choose new dates</a>
    <br><a href="/web/homeMng">Back to Main Menu</a>
    <br><a href="/web/logout">Logout</a>
</div>
</body>
</html>