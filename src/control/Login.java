package control;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User;


@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get parameters of the form
		try {
			long nusp = Long.parseLong(request.getParameter("nusp"));
			String password = request.getParameter("password");
			User us = new User(nusp);
			if (!us.getPassword().equals(password))
				throw new Exception("Número Usp/Senha inválido");
			//Adiciona sessão
			HttpSession session = request.getSession();
			session.setAttribute("nusp", nusp);
			session.setAttribute("usertype", us.getType());
			//Direciona para index
			((HttpServletRequest) request).getRequestDispatcher("index.jsp").forward(request, response);
		}
		catch (Exception e) {
			response.sendRedirect("login.jsp?msg=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
		}
		
		
		
	}

}
