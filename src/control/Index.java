package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserType;


@WebServlet("/Index")
public class Index extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserType type = (UserType) session.getAttribute("usertype");
		switch (type) {
		case Avaliador:
			response.sendRedirect("index_avaliador.jsp");
			break;
		case Descritor:
			response.sendRedirect("index_descritor.jsp");
			break;
		case DescritorAvaliador:
			response.sendRedirect("index_descritor_avaliador.jsp");
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
