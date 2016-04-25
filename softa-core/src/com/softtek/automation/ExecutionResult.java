package com.softtek.automation;

public class ExecutionResult {

	private boolean result = true;

	private String message;

	private Throwable error;
	
	private Object objectResult;

	public ExecutionResult() {
		super();
	}

	public ExecutionResult(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public ExecutionResult(boolean result, String message, Throwable error) {
		super();
		this.result = result;
		this.message = message;
		this.error = error;
	}
	
	public ExecutionResult(boolean result, String message, Throwable error, Object objectResult) {
		super();
		this.result = result;
		this.message = message;
		this.error = error;
		this.objectResult = objectResult;
	}

	
	
	public Object getObjectResult() {
		return objectResult;
	}

	public void setObjectResult(Object objectResult) {
		this.objectResult = objectResult;
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
