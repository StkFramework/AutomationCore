package com.softtek.automation.actions;

import com.softtek.automation.ExecutionResult;
import com.softtek.automation.element.UIElement;

public interface UIActions extends Actions {

	public ExecutionResult ClickOnElement(UIElement element);

	public ExecutionResult ElementHasText(UIElement element, String text);

	public ExecutionResult ElementContainsText(UIElement element, String text);

	public ExecutionResult TypeTextOnElement(UIElement element, String text);

	public ExecutionResult SelectValueFromDropdownElement(UIElement createElement, String text);

	public ExecutionResult ElementIsEnabled(UIElement element);
}
