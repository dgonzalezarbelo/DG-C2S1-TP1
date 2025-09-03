package es.ucm.tp1.supercars.control.exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughCoinsException(String message) {
		super(message);
	}
	
	public NotEnoughCoinsException(String message, Throwable cause) {
		super(message, cause);
	}
}
