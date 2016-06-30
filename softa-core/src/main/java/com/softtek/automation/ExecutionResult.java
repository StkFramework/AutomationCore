/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation;

public class ExecutionResult {

	private boolean result;
	
	private String message;

	private Throwable error;
	
	private Object objectResult;

	private ErrorType errorType;
	
	public ExecutionResult() {
		super();
		this.result = true;
		this.errorType = ErrorType.ERROR;
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

	
	public ExecutionResult(boolean result, String message, Throwable error, Object objectResult, ErrorType errorType) {
		super();
		this.result = result;
		this.message = message;
		this.error = error;
		this.objectResult = objectResult;
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
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


