package control;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserType;

public class AuthenticatorFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			//Verify if the page is css, js or Sobre.jsp
			String url = ((HttpServletRequest) request).getRequestURI();
			String regexs[] = {"(.*/(css|js)/.*)", ".*/login[.]jsp", ".*/Sobre.jsp"};
			
			for (String regex : regexs) {
				Pattern pattern = Pattern.compile(regex);
				Matcher m = pattern.matcher(url);
				if (m.matches()) {
					chain.doFilter(request, response);
					return;
				}
			}
			
			HttpSession session = ((HttpServletRequest)request).getSession();
			
			if (session.getAttribute("nusp") == null || session.getAttribute("nusp").equals("") || session.getAttribute("nusp").equals("0")) {
				// Não está logado
				if (request.getParameter("nusp") != null && !request.getParameter("nusp").equals(""))
					//Está fazendo o login
					chain.doFilter(request, response);
				else {
					((HttpServletResponse) response).sendRedirect("login.jsp");
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
