package servlet;
import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;

@WebServlet("/availableDates")
public class AvailableDatesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp user;
    private List<TripDTO> tripslist;

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/availableDatesWeb.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String aux_dep = request.getParameter("dep");
        String aux_dest = request.getParameter("dest");

        String[] split_dep = aux_dep.split("-");
        String[] split_dest = aux_dest.split("-");

        Date dep = getDate(Integer.parseInt(split_dep[2]), Integer.parseInt(split_dep[1]), Integer.parseInt(split_dep[0]));
        Date dest = getDate(Integer.parseInt(split_dest[2]), Integer.parseInt(split_dest[1]), Integer.parseInt(split_dest[0]));

        String destPage = "/web/availableDatesWeb.jsp";

        tripslist = user.listAvailableTrips(dep, dest);

        if (aux_dep==null || aux_dest==null) {
            String message = "Invalid dates";
            request.setAttribute("message", message);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("availableTrips", tripslist);
            destPage = "/web/available";
        }

        response.sendRedirect(destPage);

    }
}