package servlet;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.transaction.*;

import beans.*;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private IUserApp userApp;
    private BusUserDTO userDTO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/editWeb.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");

        String destPage = "/web/edit";
        HttpSession session = request.getSession();

        if (name==null && email==null && password==null && address==null && birth==null) { // caso não seja inserido nada
            String message = "No changes done";
            request.setAttribute("message", message);
        }

        else {
            try {

                if (!name.isEmpty()) {
                    session.setAttribute("user", userApp.editProfile("name", name, ((BusUserDTO) session.getAttribute("user")).getId()));
                }

                if (!email.isEmpty()) {
                    session.setAttribute("user", userApp.editProfile("email", email, ((BusUserDTO) session.getAttribute("user")).getId()));
                }

                if (!password.isEmpty()) {
                    session.setAttribute("user", userApp.editProfile("password", password, ((BusUserDTO) session.getAttribute("user")).getId()));
                }

                if (!address.isEmpty()) {
                    session.setAttribute("user", userApp.editProfile("address", address, ((BusUserDTO) session.getAttribute("user")).getId()));
                }

                if (!birth.isEmpty()) {
                    session.setAttribute("user", userApp.editProfile("birth", birth, ((BusUserDTO) session.getAttribute("user")).getId()));
                }

                destPage = "/WEB-INF/home.jsp";

            } catch (HeuristicRollbackException e) {
                e.printStackTrace();
            } catch (SystemException e) {
                e.printStackTrace();
            } catch (HeuristicMixedException e) {
                e.printStackTrace();
            } catch (NotSupportedException e) {
                e.printStackTrace();
            } catch (RollbackException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}