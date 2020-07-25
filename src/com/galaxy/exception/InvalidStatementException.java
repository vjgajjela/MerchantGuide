package com.galaxy.exception;

/**
 * Custom exception class for identifying invalid Roman numbers.
 */
public class InvalidStatementException extends Exception {

	/** error message. */
	private final String errorMessage;

	private static final long serialVersionUID = -4787164607429413557L;

	/**
	 * @param message
	 * 
	 *            Constructor.
	 */
	public InvalidStatementException(final String message) {
		this.errorMessage = message;
	}

	/*
	 * Overridden this method to get the message passed when the exception
	 * object is created.
	 */
	
	public String getMessage() {
		return this.errorMessage;
	}

}
