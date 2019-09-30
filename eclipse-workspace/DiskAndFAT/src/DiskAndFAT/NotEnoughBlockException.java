package DiskAndFAT;

public class NotEnoughBlockException extends Exception {

	String theMessage;
	public NotEnoughBlockException() {
		
	}
	public NotEnoughBlockException(String theMessage) {
		super(theMessage);
	}
}
