package com.dac.onlineparking.module.exception;

public class OnlineParkingGlobalException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public OnlineParkingGlobalException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
