package control;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Description;
import dao.User;


@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get parameters of the form
		try {
			long nusp = (long) request.getSession().getAttribute("nusp");
			String old_pass = request.getParameter("old_pass");
			String new_pass1 = request.getParameter("new_pass1");
			String new_pass2 = request.getParameter("new_pass2");
			User us = new User(nusp);
			if (!us.getPassword().equals(old_pass))
				throw new Exception("Senha antiga incorreta");
			if (!new_pass1.equals(new_pass2))
				throw new Exception("Senhas não batem");
			us.setPassword(new_pass1);
			us.Save();
			response.sendRedirect("DadosPessoais.jsp?msg=" + URLEncoder.encode("Senha alterada com sucesso", "UTF-8"));
		}
		catch (Exception e) {
			response.sendRedirect("DadosPessoais.jsp?msg=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
		}
		
		
		
	}

}
