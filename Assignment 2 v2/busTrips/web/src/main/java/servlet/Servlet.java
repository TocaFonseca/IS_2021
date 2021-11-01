package servlet;
import beans.ManagerApp;
import beans.UserApp;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/webaccess")
public class Servlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserApp usersDate;
    @EJB
    private ManagerApp managersData;

    /* continue */

}
