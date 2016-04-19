package com.softtek.automation.steps;

import org.junit.Assert;

import com.softtek.automation.ExecutionContext;
import com.softtek.automation.ExecutionResult;

public abstract class AbstractSteps {

	protected ExecutionContext ExecutionContext;

	public ExecutionContext getExecutionContext() {
		return this.ExecutionContext;
	}

	public void setExecutionContext(ExecutionContext executionContext) {
		this.ExecutionContext = executionContext;
	}

	protected void assertTrue(ExecutionResult execution) {
		Assert.assertTrue(execution.getMessage(), execution.isValidResult());
	}

	protected void assertTrue(boolean result, String message) {
		Assert.assertTrue(message, result);
	}

	protected void assertFalse(ExecutionResult execution) {
		Assert.assertFalse(execution.getMessage(), execution.isValidResult());
	}

	protected void assertFalse(boolean result, String message) {
		Assert.assertTrue(message, result);
	}

}
