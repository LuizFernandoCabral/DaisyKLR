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


@WebServlet("/NewDescription")
public class NewDescription extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get parameters of the form
		try {
			long nusp = (long) request.getSession().getAttribute("nusp");
			String text = request.getParameter("text");
			long image_id = Long.parseLong(request.getParameter("image_id"));
			Description des = new Description(text, nusp, image_id);
			des.Save();
			response.sendRedirect("books.jsp?msg=" + URLEncoder.encode("Descrição cadastrada", "UTF-8"));
		}
		catch (Exception e) {
			response.sendRedirect("descreverImagem.jsp?msg=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
		}
		
		
		
	}

}
