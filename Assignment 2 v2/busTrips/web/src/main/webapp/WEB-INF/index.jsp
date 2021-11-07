<html>
<body>
    <h1>Bus Trips</h1>
    <% out.print("Trips List:");%>
    <c:forEach items="{$tripsList}" var = "trip">
        <br/><c:out value="${trip.id}"/>
    </c:forEach>
</body>
</html>
