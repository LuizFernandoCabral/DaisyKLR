package connector.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Select {
	public Select(String query, SelectReader reader) {
		Connection con = null;
		Statement st = null;
		try {
			con = new Connection();
			st = con.Get().createStatement();
			ResultSet rs = st.executeQuery(query);
			reader.Read(rs);
			
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
}
