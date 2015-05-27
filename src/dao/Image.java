package dao;

import java.sql.ResultSet;

import javax.servlet.http.Part;

import connector.db.DB;
import connector.db.SelectReader;

public class Image {
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
	
	public Image(Part file, int page, long book_isbn)
	{
		
	}
	
	private long id;
	private int page;
	private String location;
	private long book_isbn;
}
