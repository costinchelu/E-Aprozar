package ro.ase.exception;

public class UserAlreadyExistException extends Exception {
	
	private static final long serialVersionUID = -3755136538811088589L;
	
	public UserAlreadyExistException() {
		super();
	}
	
	public UserAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }

    public UserAlreadyExistException(final Throwable cause) {
        super(cause);
    }
}
