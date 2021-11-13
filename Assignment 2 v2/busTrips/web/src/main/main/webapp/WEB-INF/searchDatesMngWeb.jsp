<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Search Trips</title>
</head>
<body>
<div style="text-align: center">
  <h2>Search Trips</h2>
  <h3>Choose the date range</h3>

  <form action="searchDates" method="post">
    <label for="dep">Departure date: </label>
    <input type="date" id="dep" name="dep"> <br/>

    <label for="dest">Destination date: </label>
    <input type="date" id="dest" name="dest">

    <br>${message}<br>

    <button type="submit">Search trips</button>
  </form>

  <br><a href="/web/homeMng">Back to Main Menu</a>
  <br><a href="/web/logout">Logout</a>
</div>
</body>

<script type="text/javascript">

  $(document).ready(function() {
    $("#searchDates").validate({
      rules: {
        dep: "required",
        dest: "required",
      },

      messages: {
        dep: "Please enter a valid departure date",
        dest: "Please enter a valid destination date"
      }
    });

  });
</script>
</html>