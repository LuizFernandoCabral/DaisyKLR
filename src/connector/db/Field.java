package connector.db;

/**
 * 
 * Class to represet a column in the Sql table
 * @param <T> The Column type
 */
public class Field<T> {
	
	public String getName() {
		return name;
	}
	public boolean IsPrimaryKey() {
		return primary_key;
	}
	/**
	 * Constructor default, field isn't primary key
	 * @param name column name
	 * @param value column value
	 */
	public Field(String name, T value) {
		this(name, value, false);
	}
	
	/**
	 * Constructor
	 * @param name column name
	 * @param value column value
	 * @param primary_key if it's primary key
	 */
	public Field(String name, T value, boolean primary_key) {
		this.name = name;
		this.value = value;
		this.primary_key = primary_key;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	public T getValue() {
		return this.value;
	}

	private String name;
	private T value;
	private boolean primary_key = false;
}
