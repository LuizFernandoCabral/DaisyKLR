package connector.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Table {
	public String Name() {
		return "";
	}
	
	protected List<Field> Fields() {
		return new ArrayList<Field>();
	}
}
