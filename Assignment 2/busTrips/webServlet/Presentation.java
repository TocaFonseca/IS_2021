package webServlet;
import business.ManageData;
import data.BusUser;
import data.Trip;

import java.io.IOException;

@WebServlet("/webaccess")
public class Presentation extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private ManageData busLayer;

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /* something */
    }

}
