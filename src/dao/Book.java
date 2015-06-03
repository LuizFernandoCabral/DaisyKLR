package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connector.db.DB;
import connector.db.Field;
import connector.db.SelectReader;

public class Book {
	
	public static void main(String[] args) throws Exception {
		Book B = new Book(321, "Teste3", "Luiz");
		KnowledgeArea areas = new KnowledgeArea (1);
		System.out.println("title: " + B.getTitle() + " author: " + B.getAuthor());
		B.AddKnowledgeArea(areas);
		B.SaveBook();
		B.SaveKnowledgeRelation();
		for (KnowledgeArea area : B.getKnowledegeAreas()) {
			System.out.println(" id: " + area.getId());
		}
		
	}
	
	/**
	 * Constructor that search in Database for the book - through the isbn number
	 * @param B_isbn
	 * @throws Exception
	 */
	public Book(long B_isbn) throws Exception {
		DB.Select("SELECT * from Books where isbn='"+ B_isbn + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					isbn = rs.getLong("isbn");
					title = rs.getString("title");
					author = rs.getString("authors");
					sent = rs.getInt("sent");
				}
				else
					throw new Exception("Livro inexistente");
			}
		});
	}
	

	/**
	 * Constructor to create new entry if non-existing
	 * @param KnowledgeArea_name
	 */
	public Book (long B_isbn,  String B_title, String B_author) {
		try {
			Book B = new Book(B_isbn);
			isbn = B.isbn;
			title = B.title;
			author = B.author;
			sent = B.sent;
		}
		catch (Exception e) {
			isbn = B_isbn;
			title = B_title;
			author = B_author;
			sent = 0;
			new_Book = true;
		};
	}
	
	/**
	 * Method  that searches the DB for all books with "title"
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static List<Book> searchByTitle(String title) throws Exception {
		List<Book> list = new ArrayList<Book>();
		DB.Select("SELECT * from Books where title like '%"+ title + "%'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					
					list.add(new Book(rs.getLong("isbn"), rs.getString("title"), rs.getString("authors"), rs.getInt("sent")));
				}
			}
		});
		
		return list;	
	}
	

	/**
	 * Method that searches the DB for all books by "author"
	 * @param author
	 * @return
	 * @throws Exception
   */
	public static List<Book> searchByAuthor(String author) throws Exception {
		List<Book> list = new ArrayList<Book>();
		DB.Select("SELECT * from Books where authors like '%"+ author + "%'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					
					list.add(new Book(rs.getLong("isbn"), rs.getString("title"), rs.getString("authors"), rs.getInt("sent")));
				}
			}
		});
		
		return list;
	}

	/**
	 * Private Constructor to work with search method
	 * @param ISBN
	 * @param Title
	 * @param Author
	 * @param sent
	 */
	private Book (long ISBN, String Title, String Author, int sent) {
		this.isbn = ISBN;
		this.title = Title;
		this.author = Author;
		this.sent = sent;
	}
	
	/**
	 * Method to add new areas into the book
	 * @param books
	 */
	public void AddKnowledgeArea (KnowledgeArea ... areas){
		for (KnowledgeArea area : areas)
			this.list_Area.add(area);
	}
	
	/**
	 * Method to create new Book if non-existing
	 */
	public void SaveBook() {
		if (new_Book){
			DB.InsertUpdate("Books", 
				new_Book,
				new Field<Long>("isbn", isbn),
				new Field<String>("title", title),
				new Field<String>("authors", author),
				new Field<Integer>("sent", sent)
				);
		}
		else 
			System.out.println("Book already exists in DB");
	}
	
	/**
	 * Method to create new KnowledgeArea-Book relation
	 * @throws Exception
	 */
	public void SaveKnowledgeRelation() throws Exception {
		for (KnowledgeArea area : list_Area) {
			DB.Select("select * from Books_KnowledegeAreas where knowledgearea_id=" + area.getId() + " and book_isbn="+ this.isbn, new SelectReader() {
				public void Read(ResultSet rs) throws Exception
				{
					if (!rs.next()) {
						DB.InsertUpdate("Books_KnowledegeAreas", 
							true,
							new Field<Long>("book_isbn", isbn),
							new Field<Long>("knowledgearea_id", area.getId())
						);
					}
				}
			});
		}			
	}
	
	//getters e setters
	public List<KnowledgeArea> getKnowledegeAreas() throws Exception {
		DB.Select("select * from Books_KnowledegeAreas where book_isbn='"+ isbn + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					list_Area.add(new KnowledgeArea(rs.getLong("knowledgearea_id")));
				}
			}
		});
		return list_Area;
	}
	
	public List<Image> getImages() throws Exception {
		DB.Select("select * from Images where book_isbn='"+ isbn + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					list_Img.add(new Image(rs.getLong("id")));
				}
			}
		});
		return list_Img;
	}
	
	public long getISBN(){
		return this.isbn;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public int getSent (){
		return sent;
	}
	
	public void setTitle (String Title) {
		this.title = Title;
	}
	
	public void setAuthor (String Author) {
		this.author = Author;
	}
	
	public void setSent (int Sent) {
		this.sent = Sent;
	}

	// Attributes
	private long isbn = 0;
	private String title;
	private String author;
	private int sent; // indicates if book already sent to USP's DB
	private List<KnowledgeArea> list_Area = new ArrayList<KnowledgeArea>();
	private List<Image> list_Img = new ArrayList<Image>();
	private boolean new_Book = false;
}
