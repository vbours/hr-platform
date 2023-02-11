package com.boursinos.hrplatform.exceptions;


public class HrBaseException extends Exception{

	private static final long serialVersionUID = -3560409958262459082L;

	public HrBaseException() {
		super();
	}

	public HrBaseException(Exception cause) {
		super(cause);
	}

	public HrBaseException(String message) {
		super(message);
	}

	public HrBaseException(String message, Exception cause) {
		super(message, cause);
	}
}
