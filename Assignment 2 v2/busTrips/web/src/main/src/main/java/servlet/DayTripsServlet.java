package servlet;
import java.io.IOException;
import java.text.*;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;

@WebServlet("/dateTrips")
public class DayTripsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IManagerApp mng;
    private List<TripDTO> tripslist;

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/dayTripsWeb.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String aux_date = request.getParameter("selected_date");

        String[] split_date = aux_date.split("-");

        Date day = getDate(Integer.parseInt(split_date[2]), Integer.parseInt(split_date[1]), Integer.parseInt(split_date[0]));

        String destPage = "/web/dateTrips";

        tripslist = mng.searchByDate(day);

        if (aux_date==null||tripslist.isEmpty()) {
            String message = "Invalid date";
            request.setAttribute("message", message);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("availableTrips", tripslist);
            destPage = "/web/availableMng";
        }

        response.sendRedirect(destPage);

    }
}