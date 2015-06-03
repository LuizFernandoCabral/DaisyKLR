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
	private boolean approved;
	private boolean discarded;
	private boolean new_description = false;
	
	public long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public boolean isDiscarded() {
		return discarded;
	}
	
	public void setApproved() {
		approved = true;
	}
	
	public void setDiscarded() {
		approved = true;
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
					discarded = rs.getBoolean("discarded");
					user_nusp = rs.getLong("user_nusp");
					image_id = rs.getLong("image_id");
				}
				else
					throw new Exception("Descrição inexistente");
			}
		});
	}
	
	/**
	 * Create new Description
	 */
	public Description(String text,long user_nusp,long image_id) {
		this.id = 0;
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
				new Field<Long>("user_nusp",this.user_nusp),
				new Field<Long>("image_id",this.image_id)
		);
	}
}
