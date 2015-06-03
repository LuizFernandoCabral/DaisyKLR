package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connector.db.DB;
import connector.db.SelectReader;

public class KnowledgeArea {
	
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
		DB.Select("select * from Books_KnowledegeAreas where knowledgearea_id='"+ knowledgeArea_id + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					list_book.add(new Book(rs.getLong("book_isbn")));
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
				while (rs.next()) {
					
					list.add(new KnowledgeArea(rs.getLong("id"), rs.getString("name")));
				}
			}
		});
		
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		List<KnowledgeArea> areas = KnowledgeArea.search("Teste");
		//List<Book> lista = new ArrayList<Book>();
		for (KnowledgeArea area : areas){
			System.out.println(area.name);
			System.out.println(area.id);
			//lista = area.getlist_book();
			//for (Book book : lista){}
		}
	}
	
	/**
	 * Constructor to create new entry
	 * @param KnowledgeArea_name
	 */
	public KnowledgeArea (String KnowledgeArea_name) {
		this.name = KnowledgeArea_name;
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
	 * Method to update Database
	 * @throws Exception
	 */
	public void SaveKnowledgeArea() throws Exception {
	
	}
	
	/**
	 * Method to update KnowledgeArea-Book relation
	 * @throws Exception
	 */
	public void SaveBookRelations() throws Exception {
		
	}

  // getters and setters
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
	
	private long id = 0;
	private String name;
	private List<Book> list_book = new ArrayList<Book>();
}
