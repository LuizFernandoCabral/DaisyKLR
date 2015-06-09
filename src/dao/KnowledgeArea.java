package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connector.db.DB;
import connector.db.Field;
import connector.db.Holder;
import connector.db.SelectReader;

/**
 * Class to handle the Knowledge Areas
 * @author Luiz
 *
 */
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
	}
	
	/**
	 * Constructor to create new entry or update an existing
	 * @param KnowledgeArea_name
	 */
	public KnowledgeArea (String Area_name) throws Exception {
		DB.Select("SELECT * from KnowledgeAreas where name='"+ Area_name + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					id = rs.getLong("id");
					name = Area_name;
					new_area = false;
				}
				else {
					name = Area_name;
					new_area = true;
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
		this.new_area = false;
	}
	
	/**
	 * Method to create new Knowledge Area if non-existing
	 */
	public void SaveKnowledgeArea() {
		if (new_area){
			DB.InsertUpdate("KnowledgeAreas", 
				new_area,
				new Field<Long>("name", id, true),
				new Field<String>("name", name)
				);
			new_area = false;
		}
		else 
			System.out.println("Área já existente no Banco de Dados, id = " + this.id);
	}
	
	// getters and setters
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	public long getId(){
		return this.id;
	}
	
	/**
	 * Dinamically gets books of this knowledge area
	 * @return
	 * @throws Exception
	 */
	public List<Book> getBooks() throws Exception{
		Holder<List<Book>> books_list = new Holder<List<Book>>(new ArrayList<Book>());
		DB.Select("select * from Books_KnowledegeAreas where knowledgearea_id='"+ id + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next())
					books_list.getValue().add(new Book(rs.getLong("book_isbn")));
			}
		});
		return books_list.getValue();
	}
	
	// Attributes
	private long id = 0;
	private String name;
	private boolean new_area = false;
}
