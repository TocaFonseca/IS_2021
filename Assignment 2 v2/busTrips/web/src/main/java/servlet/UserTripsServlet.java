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

@WebServlet("/userTrips")
public class UserTripsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp app;
    private List<TripDTO> tripsList;
    private BusUserDTO user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        user = (BusUserDTO) session.getAttribute("user");

        tripsList = app.listUserTrips(user.getId());

        request.setAttribute("tripsList", tripsList);
        request.getRequestDispatcher("/WEB-INF/userTripsWeb.jsp").forward(request, response);

    }
}