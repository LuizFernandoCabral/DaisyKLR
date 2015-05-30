package dao;

import java.sql.ResultSet;

import connector.db.DB;
import connector.db.SelectReader;

public class Description {
	/**** Attributes ****/
	// Description id
	private int id;
	
	// Description text
	private String text;
	
	// Author of description nusp
	private int user_nusp;
	
	// Image id
	private int image_id; 
	
	// Description approved
	private boolean approved = false;
	
	// Number of approvals of this description
	private int upVotes;
	
	// Number of disapprovals of this description
	private int downVotes;
	
	// totalVotes = upVotes + downVotes
	
	/**** Getters & setters ****/
	
	/**** Other methods ****/
	
	// Construtor que verifica se ja existe umq descriçao e cria uma descriçao que armazena no BD
	// com id, text, approved, discarded, user_nusp, image_id
	
	// Update all attributes in DB
	public void Save() {
		DB.InsertUpdate("Descriptions", false, 
				args);
	}
	
	/**
	 * Approve description
	 */
	private void approve(){
		approved = true;
	}
	
	/**
	 * Discard description
	 */
	private void remove() throws Exception {
		DB.ExecuteQuery("delete from Descriptions where id=" + id);
	}
	
	/**
	 * Increment approval score and verify if description approved
	 * @param id
	 */
	public void upVote(int id) {
		upVotes++;
		checkVotes();
	}
	
	/**
	 * Increment disapproval score and verify if description disaproved
	 * @param id
	 */
	public void downVote(int id) {
		downVotes++;
		checkVotes();
	}
	
	/**
	 * Verify if description has been definitively approved / disapproved
	 */
	private void checkVotes() {
		if (upVotes == 2) {
			approve();
		} else if (downVotes == 2) {
			remove();
		}
	}

}
