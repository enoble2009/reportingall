package com.magnus.reportingall.exceptions;

public class InvalidFieldException extends Exception {

	private static final long serialVersionUID = -961831832044271648L;

	public InvalidFieldException() {
		super();
	}

	public InvalidFieldException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFieldException(String message) {
		super(message);
	}

	public InvalidFieldException(Throwable cause) {
		super(cause);
	}

}
