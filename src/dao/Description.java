package dao;

import java.sql.ResultSet;

import connector.db.DB;
import connector.db.Field;
import connector.db.SelectReader;

public class Description {
	/**Description id**/
	private long id;
	private String text;
	/**Description approved, ie upVotes >= 2**/
	private boolean approved = false;
	/**Author of description nusp**/
	private long user_nusp;
	private long image_id; 
	/**Number of approvals of this description**/
	private int upVotes = 0;
	/**Number of disapprovals of this description**/
	private int downVotes = 0;
	//totalVotes = upVotes + downVotes
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
					throw new Exception("Usuário inexistente");
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
	public void Save() {
		DB.InsertUpdate("Descriptions", 
				new_description, 
				new Field<Long>("id",this.id,true),
				new Field<String>("text",this.text),
				new Field<Boolean>("approved",this.approved),
				//new Field<Boolean>("discarded",this.discarded),
				new Field<Long>("user_nusp",this.user_nusp),
				new Field<Long>("image_id",this.image_id),
				new Field<Integer>("upVotes",this.upVotes),
				new Field<Integer>("downVotes",this.downVotes)
				);
	}
	
	/**
	 * Approve description
	 */
	private void approve(){
		this.approved = true;
		Save(); 
	}
	/**
	 * Discard description
	 */
	private void remove() throws Exception {
		DB.ExecuteQuery("delete from Descriptions where id=" + id);
	}
	/**
	 * Increment approval score and verify if description approved
	 */
	public void upVote() {
		this.upVotes++;
		checkVotes();
	}
	/**
	 * Increment disapproval score and verify if description disapproved
	 */
	public void downVote() {
		this.downVotes++;
		checkVotes();
	}
	/**
	 * Verify if description has been definitively approved / disapproved
	 */
	private void checkVotes() {
		if (this.upVotes == 2) {
			approve();
			// Should we create a method to update only a given attribute in the DB?
			Save();
		} else if (this.downVotes == 2) {
			remove();
		}
	}
}
