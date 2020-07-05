package protagoniste;

public class ZoneDeCombatNonCompatibleException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ZoneDeCombatNonCompatibleException() {}
	
	public ZoneDeCombatNonCompatibleException(String message) {
		super(message);
	}
	
	public ZoneDeCombatNonCompatibleException(Throwable cause) {
		super(cause);
	}
	
	public ZoneDeCombatNonCompatibleException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
