package connector.db;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to handle Sql connection
 * @author ricardo
 *
 */
class Connection {

	private java.sql.Connection connection;
	private static final String SERVER_NAME = "localhost";
	private static final String DATABASE_NAME = "DaisyKLR";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	protected Connection() throws ClassNotFoundException, SQLException {
		try { 
			//Loading the default JDBC Driver 
			String driverName = "com.mysql.jdbc.Driver"; 
			Class.forName(driverName);
			//Configure the sql connection
			String url = "jdbc:mysql://" + SERVER_NAME + "/" + DATABASE_NAME; 
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD); 
			if (connection == null) { 
				throw new SQLException("Conexão nula");
			} 
		}
		catch (ClassNotFoundException e) { 
			//Driver not found
			e.printStackTrace();
			throw e; 
		} catch (SQLException e) { 
			//Could't connect
			e.printStackTrace();
			throw e;
		}
		
		connection.setAutoCommit(true);
	}
	
	protected void Close() throws SQLException {
		try {
			connection.close();
		}
		catch (SQLException e) {
			//Couldn't close connection
			e.printStackTrace();
			throw e;
		}
	}
	
	protected java.sql.Connection Get() {
		return this.connection;
	}
}
