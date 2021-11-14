package servlet;
import java.io.IOException;
import java.text.*;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.transaction.*;
import beans.*;


@WebServlet("/registerTrip")
public class RegisterTripServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IManagerApp managerApp;
    private TripDTO tripDTO;

    public RegisterTripServlet() {
        super();
    }

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/registerTripWeb.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String aux_dep = request.getParameter("depDate");
        String aux_dest = request.getParameter("destDate");
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        String capacity = request.getParameter("capacity");
        String price = request.getParameter("price");

        String[] split_dep = aux_dep.split("-");
        String[] split_dest = aux_dest.split("-");

        Date depDate = getDate(Integer.parseInt(split_dep[2].split("T")[0]), Integer.parseInt(split_dep[1]), Integer.parseInt(split_dep[0]));
        Date destDate = getDate(Integer.parseInt(split_dest[2].split("T")[0]), Integer.parseInt(split_dest[1]), Integer.parseInt(split_dest[0]));


        try {
            tripDTO = managerApp.createTrip(depDate, destDate, departure, destination, Integer.parseInt(capacity), Integer.parseInt(price));
        } catch (SystemException | RollbackException | HeuristicRollbackException | HeuristicMixedException | NotSupportedException e) {
            e.printStackTrace();
        }

        String destPage = "/WEB-INF/homeMng.jsp";

        if (tripDTO==null) {
            String message = "This trip is invalid";
            request.setAttribute("message", message);
            destPage = "/WEB-INF/registerTripWeb.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}