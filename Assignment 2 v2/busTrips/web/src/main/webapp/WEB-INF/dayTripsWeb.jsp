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
      <h2>List Trips by Date</h2>
      <form action="dateTrips" method="post">
        <label for="selected_date">Choose a date: </label>
        <input type="date" id="selected_date" name="selected_date"> <br/>
        <br>${message}<br>
        <button type="submit">Search trips</button>
      </form>

      <br><a href="/web/homeMng">Back to Main Menu</a>
      <br><a href="/web/logout">Logout</a>
    </div>
  </body>

  <script type="text/javascript">

    $(document).ready(function() {
      $("#availableDates").validate({
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