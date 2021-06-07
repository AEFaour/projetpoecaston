package fr.aston.sqli.projet.canadagalerie.exceptions;

public class NotFoundWithSuchParameterException extends Exception {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public NotFoundWithSuchParameterException() {
		super();
	}

	public NotFoundWithSuchParameterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFoundWithSuchParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundWithSuchParameterException(String message) {
		super(message);
	}

	public NotFoundWithSuchParameterException(Throwable cause) {
	}
}