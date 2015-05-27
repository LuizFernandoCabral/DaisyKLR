package dao;

/**
 * Enum with the defined user's types
 * @author ricardo
 *
 */
public enum UserType {
	Descritor(1), Avaliador(2), DescritorAvaliador(3);
	
	
	public static UserType fromInteger(int x) {
		switch(x) {
	        case 1:
	            return Descritor;
	        case 2:
	            return Avaliador;
	        case 3:
	            return DescritorAvaliador;
	        }
	       return null;
	}
	public int getType() {
		return type;
	}
	private UserType(int type) {
		this.type = type;
	}
	private int type;
}
