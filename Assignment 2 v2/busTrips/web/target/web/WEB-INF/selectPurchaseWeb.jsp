<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Purchase New Ticket</title>
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
    <h2>Purchase new Ticket</h2>
    <h3>Select a trip</h3>
    <p>You have ${user.wallet}€ in your wallet</p>

    <form action="selectPurchase" method="post">

        <c:forEach var="trip" items="${tripsList}">
            <input type="radio" id="ticket" name="ticket" value="${trip.id}">
            <label for="ticket">
                <c:out value="${trip.departure}"/> -> <c:out value="${trip.destination}"/> (${trip.price}€)
            </label><br>
        </c:forEach>

        <br>${message}<br>

        <button type="submit">Buy Ticket</button>
    </form>

    <br><a href="/web/home">Back to Main Menu</a>
    <br><a href="/web/WEB-INF/logout">Logout</a>
</div>
</body>

</html>