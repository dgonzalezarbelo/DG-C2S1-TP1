package es.ucm.tp1.supercars.control.exceptions;

public class CommandExecuteException extends GameException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandExecuteException() {
		super();
	}
	
	public CommandExecuteException(String message) {
		super(message);
	}
	
	public CommandExecuteException(String message, Throwable cause) {
		super(message, cause);
	}
}
