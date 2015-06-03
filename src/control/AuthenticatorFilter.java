package control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.UserType;

public class AuthenticatorFilter implements Filter {
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			
			if (session.getAttribute("nusp") == null || session.getAttribute("nusp").equals("") || session.getAttribute("nusp").equals("0")) {
				// Não está logado
				if (request.getParameter("nusp") != null && !request.getParameter("nusp").equals(""))
					//Está fazendo o login
					chain.doFilter(request, response);
				else {
					((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			else {
				// Está logado
				chain.doFilter(request, response);
			}
		}
		
	}

	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
