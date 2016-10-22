package ua.nure.gudkov.ST4.exception;


/**
 * An exception that provides information on a DAO layer error.
 * 
 * @author A.Gudkov
 *
 */
public class DBException extends Exception {

	private static final long serialVersionUID = -1324857769217347676L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(String message) {
		super(message);
	}

}
