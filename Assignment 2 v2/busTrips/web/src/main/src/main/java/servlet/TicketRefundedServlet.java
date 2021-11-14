package servlet;
import beans.BusUserDTO;
import beans.IUserApp;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ticketRefunded")
public class TicketRefundedServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp app;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        request.setAttribute("user", app.updateUser(((BusUserDTO)session.getAttribute("user")).getId()));
        request.setAttribute("trip", session.getAttribute("trip"));
        request.getRequestDispatcher("/WEB-INF/ticketRefundedWeb.jsp").forward(request, response);

    }
}