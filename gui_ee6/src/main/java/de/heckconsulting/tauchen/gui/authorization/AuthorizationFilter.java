package de.heckconsulting.tauchen.gui.authorization;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.heckconsulting.tauchen.core.dto.UserDTO;

public class AuthorizationFilter implements Filter {
	FilterConfig config = null;
	ServletContext servletContext = null;

	public AuthorizationFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
		servletContext = config.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");

		if (session == null || currentUser == null || currentUser.getUsername() == null) {
			session.setAttribute(Constants.ORIGINAL_VIEW_KEY, httpRequest.getPathInfo());
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/LoginPage.jsf");
		} else {
			session.removeAttribute(Constants.ORIGINAL_VIEW_KEY);
			httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			httpResponse.setDateHeader("Expires", 0); // Proxies.
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
}
