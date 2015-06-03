package dao;

import java.sql.ResultSet;

import connector.db.DB;
import connector.db.Field;
import connector.db.SelectReader;

public class Description {
	/**Description id**/
	private long id;
	private String text;
	/**Author of description nusp**/
	private long user_nusp;
	private long image_id;
	private Boolean new_description = false;
	
	private static long IDCOUNT = 0;
	
	public long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Loads existent description with given id
	 */
	public Description(long id) throws Exception {
		DB.Select("SELECT * from Descriptions where id=" + id, new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					text = rs.getString("text");
					approved = rs.getBoolean("approved");
					//discarded = rs.getBoolean("discarded");
					user_nusp = rs.getLong("user_nusp");
					image_id = rs.getLong("image_id");
					upVotes = rs.getInt("upVotes");
					downVotes = rs.getInt("downVotes");
				}
				else
					throw new Exception("Descriçao inexistente");
			}
		});
	}
	/**
	 * Create new Description
	 */
	public Description(String text,long user_nusp,long image_id) {
		this.id = IDCOUNT;
		IDCOUNT++;
		this.text = text;
		this.user_nusp = user_nusp;
		this.image_id = image_id;
		this.new_description = true;
	}
	/**
	 * Save description in DB
	 */
	public void Save() throws Exception{
		DB.InsertUpdate("Descriptions", 
				new_description, 
				new Field<Long>("id",this.id,true),
				new Field<String>("text",this.text),
				new Field<Boolean>("approved",new_description ? false : Rating.isApproved(this.id).equals("approved")),
				new Field<Long>("user_nusp",this.user_nusp),
				new Field<Long>("image_id",this.image_id)
				);
	}
	
	/**
	 * Discard description
	 */
	public void remove() throws Exception {
		DB.ExecuteQuery("delete from Descriptions where id=" + id);
	}
	
}
