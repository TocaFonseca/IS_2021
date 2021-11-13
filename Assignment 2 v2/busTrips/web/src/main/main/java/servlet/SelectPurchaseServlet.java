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

@WebServlet("/selectPurchase")
public class SelectPurchaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp app;
    private List<TripDTO> tripslist;
    private TripDTO purchased;

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Calendar aux = java.util.Calendar.getInstance();
        Date current = getDate(aux.get(Calendar.DAY_OF_MONTH), aux.get(Calendar.MONTH), aux.get(Calendar.YEAR));
        Date later = getDate(aux.get(Calendar.DAY_OF_MONTH), aux.get(Calendar.MONTH), aux.get(Calendar.YEAR)+1);
        tripslist = app.listAvailableTrips(current, later);
        request.setAttribute("tripsList", tripslist);
        request.getRequestDispatcher("/WEB-INF/selectPurchaseWeb.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ticket = request.getParameter("ticket");

        String destPage = "/web/selectPurchase";

        if (ticket != null) {

            HttpSession session = request.getSession();
            try {
                purchased = app.buyTicket(((BusUserDTO) session.getAttribute("user")).getId(), Integer.parseInt(ticket));
                session.setAttribute("trip", purchased);
                destPage = "/web/ticketPurchased";
            } catch (SystemException e) {
                e.printStackTrace();
            } catch (HeuristicRollbackException e) {
                e.printStackTrace();
            } catch (HeuristicMixedException e) {
                e.printStackTrace();
            } catch (NotSupportedException e) {
                e.printStackTrace();
            } catch (RollbackException e) {
                e.printStackTrace();
            }

        } else {
            String message = "Empty ticket";
            request.setAttribute("message", message);
        }

        response.sendRedirect(destPage);

    }
}