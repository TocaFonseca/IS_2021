package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	private HttpServletRequest httpRequest;

	private static final String[] loginRequiredURLs = {
			"/web/availableDates", "/web/availableMng", "/web/available", "/web/chargeWallet", "/web/dailySummary",
			"/web/dateTrips", "/web/deleteTrip", "/web/deleteProfile", "/web/edit", "/web/homeMng", "/web/home",
			"/web/logout", "/web/registerTrip", "/web/searchDates", "/web/searchTrips", "/web/selectPurchase",
			"/web/selectRefund", "/web/ticketPurchased", "/web/ticketRefunded", "/web/topPassengers", "/web/userTrips"
	};
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

		String loginURI = httpRequest.getContextPath() + "/web/login";
		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isLoginPage = httpRequest.getRequestURI().endsWith("/WEB-INF/loginWeb.jsp");

		if (isLoggedIn && (isLoginRequest || isLoginPage)) {
			// the user is already logged in and he's trying to login again
			// then forward to the homepage
			httpRequest.getRequestDispatcher("/").forward(request, response);

		} else if (!isLoggedIn && isLoginRequired()) {
			// the user is not logged in, and the requested page requires
			// authentication, then forward to the login page
			String loginPage = "/WEB-INF/loginWeb.jsp";
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		} else {
			// for other requested pages that do not require authentication
			// or the user is already logged in, continue to the destination
			chain.doFilter(request, response);
		}
	}


	private boolean isLoginRequired() {
		String requestURL = httpRequest.getRequestURL().toString();

		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}

		return false;
	}



	public void destroy() {
	}
}
