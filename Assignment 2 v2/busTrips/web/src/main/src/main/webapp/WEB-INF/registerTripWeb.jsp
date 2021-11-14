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
    <h1>Create a New Trip</h1>
    <form action="registerTrip" method="post">
        <label for="depDate">Departure Time:</label>
        <input type="datetime-local" id="depDate" name="depDate" size="30" />
        <br><br>
        <label for="destDate">Arrival Time:</label>
        <input type="datetime-local" id="destDate" name="destDate" size="30" />
        <br><br>
        <label for="departure">Departure Point:</label>
        <input id="departure" name="departure" size="30" />
        <br><br>
        <label for="destination">Destination Point:</label>
        <input id="destination" name="destination" size="30" />
        <br><br>
        <label for="capacity">Capacity:</label>
        <input id="capacity" type="number" min="0" max="1000" name="capacity" size="10" />
        <br><br>
        <label for="price">Price:</label>
        <input id="price" name="price" type="number" min="0" max="1000" size="10" />
        <label for="price">€</label>
        <br><br>
        <button type="submit">Create</button>
        <br><br>
        <br>${message}
        <br><a href="/web/homeMng">Back to Main Menu</a>
        <br><a href="/web/logout">Logout</a>
    </form>
</div>
</body>
</html>