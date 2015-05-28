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
	
	// Approve description
	private void approve(int id){
		approved = true;
	}
	
	// Discard description
	private void discard() {
		
	}
	
	// Vote up: increment approved and verify
	public void upVote(int id) {
		upVotes++;
		verify();
	}
	
	// Vote down: increment disapproved and verify
	public void downVote(int id) {
		downVotes++;
		verify();
	}
	
	private void verify() {
		//verify if approved / disapproved before total = 3
		if (upVotes == 2) {
			approve();
		} else if (downVotes == 2) {
			discard();
		}
	}
	
	//discarded
	
	
	
	
	
	
	public Description(String username, String userpass) {
		DB.Select("SELECT * from Users where name='"+ username + "' and password='" + userpass + "'", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					nusp = rs.getLong("nusp");
					name = rs.getString("name");
					password = rs.getString("password");
					type = rs.getInt("usertype");
				}
				else
					throw new Exception("Usuário inexistente");
			}
		});
	}
	
	
	public User(long user_nusp) {
		DB.Select("SELECT * from Users where nusp=" + user_nusp, new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					nusp = rs.getLong("nusp");
					name = rs.getString("name");
					password = rs.getString("password");
					type = rs.getInt("usertype");
				}
				else
					throw new Exception("Usuário inexistente");
			}
		});
	}
	
	public User(long nusp, String name, String password, int type) {
		this.nusp = nusp;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public void Save() throws Exception {
	}
	
	private long nusp = 0;
	private String name;
	private String password;
	private int type;

}
