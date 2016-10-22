package ua.nure.gudkov.ST4.exception;


/**
 * An exception that provides information on a database access error.
 * 
 * @author A.Gudkov
 *
 */
public class DAOException extends Exception {
	
	private static final long serialVersionUID = -2978272214880644372L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

}
