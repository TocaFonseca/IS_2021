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
    <h2>Charge Your Wallet</h2>
    <p>You have ${user.wallet}€ in your wallet</p>

    <form action="chargeWallet" method="post">
        <label for="amount">Amount:</label>
        <input id="amount" type="number" min="0" max="1000" name="amount" size="10" />
        <label for="amount">€</label>
        <br>${message}
        <br><br>
        <button type="submit">Charge it!</button>
    </form>

    <br><a href="/web/home">Back to Main Menu</a>
    <br><a href="/web/logout">Logout</a>
</div>
</body>
</html>