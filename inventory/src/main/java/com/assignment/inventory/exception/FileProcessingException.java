package com.assignment.inventory.exception;

public class FileProcessingException extends RuntimeException {

	public FileProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileProcessingException(String message) {
		super(message);
	}

	public FileProcessingException(Throwable cause) {
		super(cause);
	}

}
