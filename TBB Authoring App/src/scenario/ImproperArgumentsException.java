package scenario;

/**
 * Exception for invalid amount or type of arguments for a specific command
 * 
 * @author DKozlovsky
 *
 */
public class ImproperArgumentsException extends Exception {

	public ImproperArgumentsException() {
		super();
		
	}

	public ImproperArgumentsException(String message) {
		super(message);
		
	}

	

}
