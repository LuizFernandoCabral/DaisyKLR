package connector.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Class to handle select, update, insert and delete
 *
 */
public class DB {
	
	/**
	 * Do a Select
	 * @param query the query to be executed
	 * @param selectReader the anonymous class with the method Read that reads the ResultSet
	 * @throws Exception 
	 */
	public static void Select(String query, SelectReader selectReader) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			con = new Connection();
			st = con.Get().createStatement();
			ResultSet rs = st.executeQuery(query);
			selectReader.Read(rs);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			//Probably an user defined error
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
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to insert or update data
	 * @param table_name name of the table
	 * @param insert true if it's insert or false if it's update (register already exists)
	 * @param args all the columns to be set
	 * @return último PK inserido no BD
	 */
	public static long InsertUpdate(String table_name, boolean insert, Field<?> ... args) {
		long id = -1;
		String sqlQuery = "";
		if (!insert) {
			sqlQuery = "Update " + table_name + " set ";
			String where = "";
			for (Field<?> f : args) {
				sqlQuery += f.getName() + " = ?,";
				if (f.IsPrimaryKey()) {
					where = " WHERE " + f.getName() + " = " + f.getValue();
					id = (long) f.getValue();
				}
			}
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + where;
		}
		else {
			String temp = " VALUES (";
			sqlQuery = "Insert into " + table_name + " (";
			
			for (Field<?> f : args) {
				sqlQuery += f.getName() + ",";
				temp += "?,";
			}
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ")";
			sqlQuery += temp;
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ")";
		}
		
		Connection con = null;
		PreparedStatement update_insert = null;
		try {
			con = new Connection();
			update_insert = con.Get().prepareStatement(sqlQuery);
			for (int i = 0; i < args.length; i++) {
				update_insert.setObject(i+1, args[i].getValue());
			}
			update_insert.executeUpdate();
			if (insert) {
				//Pega o id
				Statement st = con.Get().createStatement();
				ResultSet rs = st.executeQuery("select last_insert_id() as last_id");
				rs.next();
				id = rs.getLong("last_id");
				st.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (update_insert != null)
					update_insert.close();
				if (con != null)
					con.Close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	/**
	 * Executes a query. Use it to remove data (delete)
	 * @param query
	 * @throws SQLException if the query returns an error
	 */
	public static void ExecuteQuery(String query) throws SQLException {
		Connection con = null;
		Statement st = null;
		try {
			con = new Connection();
			st = con.Get().createStatement();
			st.execute(query);
			
		} catch (ClassNotFoundException e) {
			//Couldn't find the class
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
				e.printStackTrace();
			}
		}
	}
}
