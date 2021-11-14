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

@WebServlet("/dailySummary")
public class DailySummaryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IManagerApp app;
    private Map<TripDTO, Integer> tripsRevenue;
    private BusUserDTO user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        tripsRevenue = app.dailyRevenue();

        request.setAttribute("revenueList", tripsRevenue);
        request.getRequestDispatcher("/WEB-INF/dailySummaryWeb.jsp").forward(request, response);

    }
}