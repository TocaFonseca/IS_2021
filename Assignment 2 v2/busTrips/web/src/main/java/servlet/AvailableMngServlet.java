package servlet;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;

@WebServlet("/availableMng")
public class AvailableMngServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private List<TripDTO> tripsList;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        tripsList = (List<TripDTO>) session.getAttribute("availableTrips");

        System.out.println("===== users: " + tripsList.get(0).getUsers());

        request.setAttribute("tripsList", tripsList);
        request.getRequestDispatcher("/WEB-INF/availableMngWeb.jsp").forward(request, response);

    }
}