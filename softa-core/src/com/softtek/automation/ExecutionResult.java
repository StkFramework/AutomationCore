package com.softtek.automation;

public class ExecutionResult {

	private boolean result = true;

	private String message;

	private Throwable error;

	public ExecutionResult() {
		super();
	}

	public ExecutionResult(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public boolean isValidResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}

}
