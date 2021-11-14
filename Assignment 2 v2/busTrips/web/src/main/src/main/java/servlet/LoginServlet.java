package servlet;
import java.io.IOException;
import java.security.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import beans.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp userApp;

    private BusUserDTO userDTO;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/loginWeb.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        userDTO = new BusUserDTO();

        try {
            userDTO = userApp.authentication(password, email);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        String destPage = "/web/login";


        if (userDTO!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userDTO);
            destPage = "/WEB-INF/home.jsp";
        } else {
            // TODO o caso invalido nao esta a funcionar bem
            String message = "Invalid email/password";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}