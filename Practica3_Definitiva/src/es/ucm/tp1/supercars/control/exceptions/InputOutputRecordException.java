package es.ucm.tp1.supercars.control.exceptions;

public class InputOutputRecordException extends CommandExecuteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputOutputRecordException(String message) {
		super(message);
	}
	
	public InputOutputRecordException(String message, Throwable cause) {
		super(message, cause);
	}
}
