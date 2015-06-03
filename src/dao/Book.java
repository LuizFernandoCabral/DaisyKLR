package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connector.db.DB;
import connector.db.SelectReader;

public class Book {
	
	public Book(long B_isbn) throws Exception {
		DB.Select("SELECT * from Books where isbn='"+ B_isbn + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					isbn = rs.getLong("isbn");
					title = rs.getString("title");
					author = rs.getString("author");
					sent = rs.getInt("sent");
				}
				else
					throw new Exception("Livro inexistente");
			}
		});
	}
	
	public static List<Book> searchByTitle(String title) throws Exception {
		List<Book> list = new ArrayList<Book>();
		DB.Select("SELECT * from Books where title like '%"+ title + "%'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					
					list.add(new Book(rs.getLong("isbn"), rs.getString("title"), rs.getString("author"), rs.getInt("sent")));
				}
			}
		});
		
		return list;	
	}
	
	
	
	public static List<Book> searchByAuthor(String author) throws Exception {
		List<Book> list = new ArrayList<Book>();
		DB.Select("SELECT * from Books where author like '%"+ author + "%'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					
					list.add(new Book(rs.getLong("isbn"), rs.getString("title"), rs.getString("author"), rs.getInt("sent")));
				}
			}
		});
		
		return list;
	}

	private Book (long ISBN, String Title, String Author, int sent) {
		this.isbn = ISBN;
		this.title = Title;
		this.author = Author;
		this.sent = sent;
	}
	
	public void SaveBook() {
		
	}
	
	public void SaveKnowledgeRelation() {
		
	}
	
	//getters e setters
	public List<KnowledgeArea> getKnowledegeAreas() {
		return null;
	}
	
	public List<Image> getImages() {
		return null;
	}
	
	public int getSent (){
		return sent;
	}
	
	public void setSent (int Sent) {
		this.sent = Sent;
	}

	private long isbn = 0;
	private String title;
	private String author;
	private int sent;
}
