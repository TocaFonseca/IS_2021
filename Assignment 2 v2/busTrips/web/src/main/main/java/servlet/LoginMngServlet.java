package servlet;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import beans.*;

@WebServlet("/loginMng")
public class LoginMngServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IManagerApp mngApp;
    private BusUserDTO userDTO;

    public LoginMngServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/loginMngWeb.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        userDTO = new BusUserDTO();

        userDTO = mngApp.authentication(password, email);
        String destPage = "/WEB-INF/loginMngWeb.jsp";

        if (userDTO!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userDTO);
            destPage = "/WEB-INF/homeMng.jsp";
        } else {
            // TODO o caso invalido nao esta a funcionar bem
            String message = "Invalid email/password";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}