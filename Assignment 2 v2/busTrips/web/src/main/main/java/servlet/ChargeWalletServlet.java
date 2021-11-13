package servlet;
import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.*;

import beans.*;

@WebServlet("/chargeWallet")
public class ChargeWalletServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp userApp;
    private BusUserDTO userDTO;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/chargeWalletWeb.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String amount = request.getParameter("amount");
        String destPage = "/WEB-INF/chargeWalletWeb.jsp";
        HttpSession session = request.getSession();

        if (amount.equals("")) { // caso não seja inserido nada
            String message = "No ammount added";
            request.setAttribute("message", message);

        } else {
            try {
                userDTO = (BusUserDTO) session.getAttribute("user");
                session.setAttribute("user", userApp.chargeWallet(userDTO.getId(), amount));
                destPage = "/WEB-INF/home.jsp";

            } catch (HeuristicRollbackException | SystemException | HeuristicMixedException | NotSupportedException | RollbackException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}