package servlet;
import beans.IManagerApp;
import beans.IUserApp;
import beans.TripDTO;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/deleteTrip")
public class DeleteTripServlet extends HttpServlet {

    @EJB
    private IManagerApp mngApp;
    @EJB
    private IUserApp app;
    private List<TripDTO> tripslist;

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
        request.getRequestDispatcher("/WEB-INF/deleteTripWeb.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ticket = request.getParameter("ticket");

        try {
            mngApp.deleteTrip(Integer.parseInt(ticket));
        } catch (HeuristicRollbackException | SystemException | HeuristicMixedException | RollbackException | NotSupportedException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/homeMng.jsp");
        dispatcher.forward(request, response);
    }

}
