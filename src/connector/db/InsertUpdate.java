package connector.db;

import java.sql.ResultSet;
import java.sql.Statement;

public class InsertUpdate {
	public InsertUpdate(String query) {
		Connection con = null;
		Statement st = null;
		try {
			con = new Connection();
			st = con.Get().createStatement();
			st.execute(query);
			
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
