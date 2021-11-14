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

@WebServlet("/topPassengers")
public class TopPassengersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IManagerApp app;
    private List<BusUserDTO> topList;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        topList = app.topPassengers();

        request.setAttribute("topList", topList);
        request.getRequestDispatcher("/WEB-INF/topPassengersWeb.jsp").forward(request, response);

    }
}