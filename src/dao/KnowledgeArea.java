package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connector.db.DB;
import connector.db.Field;
import connector.db.SelectReader;

public class KnowledgeArea {
	
	public static void main(String[] args) throws Exception {
		KnowledgeArea areas = new KnowledgeArea ("Banana");
		//List<Book> lista = new ArrayList<Book>();
		//for (KnowledgeArea area : areas){
			//System.out.println(area.name);
			//System.out.println(area.id);
			//lista = area.getlist_book();
			//for (Book book : lista){}
		//}
		areas.SaveKnowledgeArea();
	}
	/**
	 * Constructor that search in Database for the Knowledge Area - through the id
	 * @param knowledgeArea_id
	 */
	public KnowledgeArea(long knowledgeArea_id) throws Exception {
		DB.Select("SELECT * from KnowledgeAreas where id='"+ knowledgeArea_id + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					id = rs.getLong("id");
					name = rs.getString("name");
				}
				else
					throw new Exception("Área de Conhecimento inexistente");
			}
		});
	}
	
	/**
	 * Constructor to create new entry
	 * @param KnowledgeArea_name
	 */
	public KnowledgeArea (String Area_name) throws Exception {
		DB.Select("SELECT * from KnowledgeAreas where name like '"+ Area_name + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					id = rs.getLong("id");
					name = Area_name;
					new_Area = false;
				}
				else {
					name = Area_name;
					new_Area = true;
				}
			}
		});
	}

    /**
     * Method that searches the database for all Knowledge areas - through name, can be more than one
     * @param Area - name
     * @return list<KnowledgeArea> with Area's name
     */
	public static List<KnowledgeArea> search(String Area) throws Exception {
		List<KnowledgeArea> list = new ArrayList<KnowledgeArea>();
		DB.Select("SELECT * from KnowledgeAreas where name like '%"+ Area + "%'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next())
					list.add(new KnowledgeArea(rs.getLong("id"), rs.getString("name")));
			}
		});
		
		return list;
	}

	/**
	 * Private Constructor to work with search method
	 * @param KnowledgeArea_id
	 * @param KnowledgeArea_name
	 */
	private KnowledgeArea (long KnowledgeArea_id, String KnowledgeArea_name) {
		this.id = KnowledgeArea_id;
		this.name = KnowledgeArea_name;
	}
	
	/**
	 * Method to add new books into the Knowleadge area
	 * @param books
	 */
	public void AddBooks (Book ... books){
		for (Book book : books)
			this.list_book.add(book);
	}
	
	/**
	 * Method to create new Knowledge Area if non-existing
	 */
	public void SaveKnowledgeArea() {
		if (new_Area){
			DB.InsertUpdate("KnowledgeAreas", 
				new_Area,
				new Field<String>("name", name)
				);
		}
		else 
			System.out.println("Área já existente no Banco de Dados, id = " + this.id);
	}
	
	/**
	 * Method to create new KnowledgeArea-Book relation
	 * @throws Exception
	 */
	public void SaveBookRelations() throws Exception {
		for (Book book : list_book){
			DB.Select("select * from Books_KnowledegeAreas where knowledgearea_id=" + this.id + " and book_isbn="+ book.getISBN(), new SelectReader() {
				public void Read(ResultSet rs) throws Exception
				{
					if (!rs.next()) {
						DB.InsertUpdate("Books_KnowledgeAreas", 
							true,
							new Field<Long>("book_isbn", book.getISBN()),
							new Field<Long>("knowledgearea_id", id)
						);
					}
				}
			});
		}
	}

	// getters and setters
	/**
	 * Gets books from DB
	 * @throws Exception
	 */
	public void setListBook() throws Exception {
		DB.Select("select * from Books_KnowledegeAreas where knowledgearea_id='"+ id + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					list_book.add(new Book(rs.getLong("book_isbn")));
				}
			}
		});
	}
	
	public void setName(String Name){
		this.name=Name;
	}
	
	public String getName(){
		return this.name;
	}

	public long getId(){
		return this.id;
	}
	
	public List<Book> getlist_book (){
		return this.list_book;
	}
	
	// Attributes
	private long id = 0;
	private String name;
	private List<Book> list_book = new ArrayList<Book>();
	private boolean new_Area = false;
}
