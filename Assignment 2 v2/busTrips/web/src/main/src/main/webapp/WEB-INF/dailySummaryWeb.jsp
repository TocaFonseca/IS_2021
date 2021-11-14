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
    <h2>Daily Revenue</h2>
    <c:forEach var="trip" items="${revenueList}">
        <p>
            <c:out value="${trip.key.destination}"/> -> <c:out value="${trip.key.departure}"/>: <c:out value="${trip.value}"/>
        </p>
    </c:forEach>

    <br><a href="/web/homeMng">Back to Main Menu</a>
    <br><a href="/web/logout">Logout</a>
</div>
</body>
</html>