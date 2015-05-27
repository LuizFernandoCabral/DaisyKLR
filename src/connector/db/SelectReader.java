package connector.db;

import java.sql.ResultSet;

public interface SelectReader {
	
	public void Read(ResultSet rs) throws Exception;
}