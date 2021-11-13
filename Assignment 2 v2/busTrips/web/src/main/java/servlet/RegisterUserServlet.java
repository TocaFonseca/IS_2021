package servlet;
import java.io.IOException;
import java.text.*;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.transaction.*;
import beans.*;


@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp userApp;
    private BusUserDTO userDTO;

    public RegisterUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/registerUserWeb.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDTO = new BusUserDTO();
        Date birth=null;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String date = request.getParameter("date");
        String address = request.getParameter("address");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            birth = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            userDTO = userApp.register(name, birth, email, password, address);
        } catch (SystemException | RollbackException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        }

        String destPage = "/WEB-INF/loginWeb.jsp";

        if (userDTO!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userDTO);
            destPage = "/WEB-INF/home.jsp";
        } else {
            String message = "This email is already registered... Try logging in!";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}