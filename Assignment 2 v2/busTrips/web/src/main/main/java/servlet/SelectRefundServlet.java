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
import javax.transaction.*;

import beans.*;

@WebServlet("/selectRefund")
public class SelectRefundServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp app;
    private List<TripDTO> tripsList;
    private BusUserDTO user;
    private TripDTO refunded;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        user = (BusUserDTO) session.getAttribute("user");
        tripsList = app.listFutureUserTrips(user.getId());
        request.setAttribute("tripsList", tripsList);
        request.getRequestDispatcher("/WEB-INF/selectRefundWeb.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ticket = request.getParameter("ticket");

        String destPage = "/web/selectRefund";

        if (ticket != null) {
            HttpSession session = request.getSession();
            try {
                refunded = app.returnTicket(((BusUserDTO) session.getAttribute("user")).getId(), Integer.parseInt(ticket));
                session.setAttribute("trip", refunded);
                destPage = "/web/ticketRefunded";
            } catch (SystemException | HeuristicRollbackException | HeuristicMixedException | NotSupportedException | RollbackException e) {
                e.printStackTrace();
            }

        } else {
            String message = "Empty ticket";
            request.setAttribute("message", message);
        }

        response.sendRedirect(destPage);

    }
}