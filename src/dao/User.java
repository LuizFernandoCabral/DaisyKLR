package dao;

import java.sql.ResultSet;

import connector.db.DB;
import connector.db.Field;
import connector.db.SelectReader;

public class User {
	/**
	 * Loads existent user with given nusp
	 * @param user_nusp
	 * @throws Exception if the user does't exist
	 */
	public User(long user_nusp) throws Exception {
		DB.Select("SELECT * from Users where nusp=" + user_nusp, new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					nusp = rs.getLong("nusp");
					name = rs.getString("name");
					password = rs.getString("password");
					type = UserType.fromInteger(rs.getInt("usertype"));
				}
				else
					throw new Exception("Usuário inexistente");
			}
		});
	}
	/**
	 * Creates new user
	 * @param nusp
	 * @param name
	 * @param password
	 * @param type
	 */
	public User(long nusp, String name, String password, UserType type) {
		this.nusp = nusp;
		this.name = name;
		this.password = password;
		this.type = type;
		this.new_user = true;
	}

	/**
	 * Save user on database
	 */
	public void Save() {
		DB.InsertUpdate("Users", 
				new_user,
				new Field<Long>("nusp", nusp, true),
				new Field<String>("name", name),
				new Field<String>("password", password),
				new Field<Integer>("usertype", type.getType())
				);
	}
	/**
	 * Removes the user
	 * @throws Exception if couldn't remove the user (due to constraints)
	 */
	public void Remove() throws Exception {
		DB.ExecuteQuery("delete from Users where nusp=" + nusp);
	}
	
	public long getNusp() {
		return nusp;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public UserType getType() {
		return type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	
	//User data
	private long nusp = 0;
	private String name;
	private String password;
	private UserType type;
	private boolean new_user = false;
	
}
