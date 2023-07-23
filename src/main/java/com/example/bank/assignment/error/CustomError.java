package com.example.bank.assignment.error;

import java.util.List;

public class CustomError {

	private String message;
	private List<String> validationErrors;

	public CustomError(String message) {
		this.setMessage(message);
	}

	public CustomError(String message, List<String> validationErrors) {
		this.setMessage(message);
		this.setValidationErrors(validationErrors);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}

}
