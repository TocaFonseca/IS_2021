package servlet;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.transaction.*;

import beans.*;

@WebServlet("/deleteProfile")
public class DeleteUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp userApp;

    private BusUserDTO userDTO;

    public DeleteUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/deleteUserWeb.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        userDTO = (BusUserDTO) session.getAttribute("user");
        String password = request.getParameter("password");
        boolean deleted = false;

        try {
            deleted = userApp.deleteProfile(userDTO.getId(), password);
        } catch (HeuristicRollbackException | SystemException | HeuristicMixedException | RollbackException | NotSupportedException e) {
            e.printStackTrace();
        }

        if (deleted){
            session.removeAttribute("user");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/loginWeb.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
            dispatcher.forward(request, response);
        }
    }
}