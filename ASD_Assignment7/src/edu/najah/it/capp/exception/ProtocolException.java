package edu.najah.it.capp.exception;

public class ProtocolException extends Exception {

	
	public ProtocolException(String message) {
        super(message);
    }
	
	@Override
	public String getMessage() {
		String message = super.getMessage();
		return "Unexpected error in protocol: " + message;
		
	}

}
