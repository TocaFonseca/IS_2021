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

@WebServlet("/availabletrip")
public class AvailableServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp manageUser;
    private List<Trip> tripsList;

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //tripsList = manageUser.listAvailableTrips(getDate(20, 12, 2020), getDate(25, 12, 2021));
        List<Integer> tripsList = manageUser.listAvailableTrips(getDate(20, 12, 2021), getDate(25, 12, 2021)).stream().map(Trip::getTripID).collect(Collectors.toList());
        //tripsList.add(new Trip(getDate(21, 2, 2022), getDate(21, 2, 2022), "coimbra", "coimbra", 1, 1));

        request.setAttribute("tripsList", tripsList);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

    }
}