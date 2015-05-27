package connector.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Connection {
	public static void main(String[] args) {
		try {
			Connection c = new Connection();
			System.out.println("Conectou!");
			c.Close();
			System.out.println("Fechou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private java.sql.Connection connection;
	private static final String SERVER_NAME = "localhost";
	private static final String DATABASE_NAME = "daisyklr";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	protected Connection() throws Exception {
		try { 
			// Carregando o JDBC Driver padrão 
			String driverName = "com.mysql.jdbc.Driver"; 
			Class.forName(driverName); // Configurando a nossa conexão com um banco de dados
			String url = "jdbc:mysql://" + SERVER_NAME + "/" + DATABASE_NAME; 
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD); 
			if (connection != null) { 
				System.out.println("Conectou!");
			} else { 
				throw new Exception("Conexão nula");
			} 
		}
		catch (ClassNotFoundException e) { 
			//Driver not found
			System.out.println("O driver expecificado nao foi encontrado."); 
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
