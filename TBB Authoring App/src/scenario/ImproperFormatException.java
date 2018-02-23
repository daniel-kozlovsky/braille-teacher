package scenario;

/**
 * Exception for invalid amount or type of arguments for a specific command
 * 
 * @author DKozlovsky
 *
 */
public class ImproperFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImproperFormatException() {
		super();

	}

	public ImproperFormatException(String message) {
		super(message);

	}

}
