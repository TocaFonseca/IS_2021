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
    private IGetData getData;

    private List<Trip> tripsList;
    private Date depD;
    private Date dest;

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<TripDTO> tripsList = new ArrayList<TripDTO>();
        tripsList = getData.listAvailableTrips(getDate(20, 12, 2020), getDate(25, 12, 2021));

        request.setAttribute("tripsList", tripsList);
        request.getRequestDispatcher("/WEB-INF/availableWeb.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String dep = request.getParameter("depDate");
        String dest = request.getParameter("destDate");

        System.out.println(dep);
        //String[] depSplit = dep.split()

    }
}