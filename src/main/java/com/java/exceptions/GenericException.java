package com.java.exceptions;

@SuppressWarnings("serial")
public class GenericException extends Exception {
	public GenericException() {
		super("Exception thrown");
	}

	public GenericException(String errorMessage) {
		super(errorMessage);
	}

	public GenericException(String errorMessage, Exception ex) {
		super(errorMessage, ex);
	}
}