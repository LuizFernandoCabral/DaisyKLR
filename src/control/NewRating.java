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
import dao.Rating;
import dao.User;


@WebServlet("/NewRating")
public class NewRating extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get parameters of the form
		try {
			long nusp = (long) request.getSession().getAttribute("nusp");
			String option = request.getParameter("Evaluate");
			long description_id = Long.parseLong(request.getParameter("description_id"));
			System.out.println(option);
			System.out.println(description_id);
			if (option.equals("Accept")) {
				Rating rating = new Rating(nusp, true, description_id);
				rating.Save();
				Description d = new Description(description_id);
				//Approved
				System.out.println(d.getPositiveRates());
				if (d.getPositiveRates() == Description.N_APPROVED) {
					d.setApproved();
					d.Save();
				}
			}
			else if (option.equals("Decline")) {
				Rating rating = new Rating(nusp, false, description_id);
				rating.Save();
				Description d = new Description(description_id);
				d.setDiscarded();
				d.Save();
			}
			else {
				//No option selected
				response.sendRedirect("avaliarDescricao.jsp?msg=" + URLEncoder.encode("Aceite ou rejeite a descrição", "UTF-8"));
			}
			response.sendRedirect("books.jsp?msg=" + URLEncoder.encode("Avaliação salva", "UTF-8"));
		}
		catch (Exception e) {
			response.sendRedirect("avaliarDescricao.jsp?msg=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
		}
		
		
		
	}

}
