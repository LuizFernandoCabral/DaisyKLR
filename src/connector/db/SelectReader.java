package connector.db;

import java.sql.ResultSet;

/**
 * Interface to be implemented when querying data
 * @author ricardo
 *
 */
public interface SelectReader {
	/**
	 * Method to be called when data is selected
	 * @param rs
	 * @throws Exception for user defined errors
	 */
	public void Read(ResultSet rs) throws Exception;
	
}
