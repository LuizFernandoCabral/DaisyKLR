package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import connector.db.DB;
import connector.db.Field;
import connector.db.SelectReader;

public class Image {
	
	/**
	 * Get existing image
	 * @param image_id
	 * @throws Exception
	 */
	public Image(long image_id) throws Exception {
		DB.Select("SELECT * from Images where id=" + image_id, new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					id = rs.getLong("id");
					location = rs.getString("location");
					page = rs.getInt("page");
					book_isbn = rs.getLong("book_isbn");
				}
				else
					throw new Exception("Imagem inexistente");
			}
		});
	}
	
	public long getId() {
		return id;
	}
	public int getPage() {
		return page;
	}
	public File getImage() {
		File f = new File(IMAGES_FOLDER + location);
		return f;
	}
	public String getLocation() {
		return location;
	}
	
	/**
	 * Create new Image
	 * @param file image file
	 * @param page
	 * @param book_isbn
	 */
	public Image(File file, int page, long book_isbn)
	{
		tempFile = file;
		this.page = page;
		this.book_isbn = book_isbn;
	}
	
	/**
	 * Save Image on database
	 * @throws IOException 
	 */
	public void Save() throws IOException {
		this.id = DB.InsertUpdate("Images", 
				true,
				new Field<Long>("id", id, true),
				new Field<String>("location", ""),
				new Field<Integer>("page", page),
				new Field<Long>("book_isbn", book_isbn)
				);
		if (tempFile != null) {
			this.location = "img" + id + "." + tempFile.getName().split("[.]")[tempFile.getName().split("[.]").length-1];
			DB.InsertUpdate("Images", 
					false,
					new Field<Long>("id", id, true),
					new Field<String>("location", location)
					);
			File dest = new File(IMAGES_FOLDER + location);
			File folder = new File(IMAGES_FOLDER);
			System.out.println(folder.exists());
			if (!folder.exists())
				folder.mkdirs();
			Files.copy(tempFile.toPath(), dest.toPath());
			tempFile = null;
		}
	}
	private static final String IMAGES_FOLDER = "/daisyklr/images/";
	
	public static void main(String[] args) throws IOException {
		Image im = new Image(new File("/Users/ricardo/Downloads/revolucao_bichos1.jpg"), 10, 1);
		im.Save();
		System.out.println("Salvou");
	}
	private File tempFile = null;
	private long id = 0;
	private int page;
	private String location;
	private long book_isbn;
}
