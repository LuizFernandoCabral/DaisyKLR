package control;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Image;

@WebServlet("/ViewImage")
public class ViewImage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("image/jpeg");
		long image_id = Long.parseLong(request.getParameter("id"));
		try {
			Image im = new Image(image_id);
			File image = im.getImage();
			response.setHeader("Content-Type", getServletContext().getMimeType(image.getName()));
			response.setHeader("Content-Length", String.valueOf(image.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

			BufferedInputStream input = null;
			BufferedOutputStream output = null;

			try {
			    input = new BufferedInputStream(new FileInputStream(image));
			    output = new BufferedOutputStream(response.getOutputStream());
			    byte[] buffer = new byte[8192];
			    for (int length = 0; (length = input.read(buffer)) > 0; ) {
			        output.write(buffer, 0, length);
			    }
			} finally {
			    if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
			    if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}


}
