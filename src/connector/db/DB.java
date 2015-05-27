package connector.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DB {
	public static void Select(String query, SelectReader selectReader) {
		Connection con = null;
		Statement st = null;
		try {
			con = new Connection();
			st = con.Get().createStatement();
			ResultSet rs = st.executeQuery(query);
			selectReader.Read(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (st != null)
					st.close();
				if (con != null)
					con.Close();
			}
			catch (Exception e) {
				
			}
		}
	}
	
	public static void InsertUpdate(Table table, Object ... args) {
		boolean insert = false;
		String sqlQuery = "";
		if (insert) {
			sqlQuery = "Update " + table.Name() + " set ";
			for (Field f : table.Fields()) {
				sqlQuery += f.Name() + " = ?,";
			}
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
		}
		else {
			String temp = " VALUES (";
			sqlQuery = "Insert into " + table.Name() + " (";
			
			for (Field f : table.Fields()) {
				sqlQuery += f.Name() + ",";
				temp += "?,";
			}
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ")";
			sqlQuery += temp;
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ")";
		}
		
		for (Object obj : args) {
			Class<? extends Object> type = obj.getClass();
			type.cast(obj);
		}
	//	Connection con = new Connection();
		//PreparedStatement preparedStmt = conn.prepareStatement(query);
	}
	
	public static void InsertUpdate(String query) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			con = new Connection();
			st = con.Get().createStatement();
			st.execute(query);
			
		} catch (Exception e) {
			//Couldn't execute query
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (st != null)
					st.close();
				if (con != null)
					con.Close();
			}
			catch (Exception e) {
				throw e;
			}
		}
	}
}
