package servlet;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.*;
import data.*;

@WebServlet("/webaccess")
public class MyServlet extends HttpServlet {

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    private static final long serialVersionUID = 1L;
    @EJB
    private IUserApp manageUser;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Integer> field1List = manageUser.listAvailableTrips(getDate(20, 12, 2021), getDate(25, 12, 2021)).stream().
                map(Trip::getTripID).collect(Collectors.toList());
        String result = "Trips list: " + field1List;
        System.out.println(result);
        response.getWriter().print(result);
    }
}