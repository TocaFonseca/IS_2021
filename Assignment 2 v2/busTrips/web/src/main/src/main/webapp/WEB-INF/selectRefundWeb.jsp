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
    <h2>Refund a Ticket</h2>
    <h2>${user.name}'s trips list</h2>

    <form action="selectRefund" method="post">
        <c:forEach var="trip" items="${tripsList}">
            <input type="radio" id="ticket" name="ticket" value="${trip.id}">
            <label for="ticket">
                <c:out value="${trip.departure}"/> -> <c:out value="${trip.destination}"/> (${trip.price}€)
            </label><br>
        </c:forEach>
        <br>${message}<br>

        <button type="submit">Refund Ticket</button>
    </form>

    <br><a href="/web/home">Back to Main Menu</a>
    <br><a href="/web/logout">Logout</a>
</div>
</body>
</html>