package dao;

import java.sql.ResultSet;

import connector.db.DB;
import connector.db.SelectReader;

public class User {
	public User(String username, String userpass) {
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
	
	public static void main(String[] args) {
		User us = new User("Ricardo", "senha");
		System.out.println(us.name);
		System.out.println(us.nusp);
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
