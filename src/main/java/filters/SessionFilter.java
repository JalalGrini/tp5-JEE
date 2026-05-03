package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class SessionFilter extends HttpFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest  request  = (HttpServletRequest)  req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession         session  = request.getSession(false);

		String action = request.getParameter("action");
		boolean isLoginAction = "login".equals(action) || action == null;

		if (isLoginAction || (session != null && session.getAttribute("user") != null)) {
			chain.doFilter(req, resp);
		} else {
			response.sendRedirect(request.getContextPath() + "/Controller?action=login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}
