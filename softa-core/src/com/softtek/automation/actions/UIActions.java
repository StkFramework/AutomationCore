package com.softtek.automation.actions;

import com.softtek.automation.ExecutionResult;
import com.softtek.automation.element.UIElement;

public interface UIActions extends Actions {

	public ExecutionResult ClickOnElement(UIElement element);

	public ExecutionResult ElementHasText(UIElement element, String text);

	public ExecutionResult ElementContainsText(UIElement element, String text);

	public ExecutionResult TypeTextOnElement(UIElement element, String text);

	public ExecutionResult SelectValueFromDropdownElement(UIElement createElement, String text);

	public ExecutionResult IsDisable(UIElement element);
	
	public ExecutionResult VerifyMaxLengthText(UIElement element, int length);
	
	public ExecutionResult IsSelected(UIElement element);
	
	public ExecutionResult MoveFocusTo(UIElement element);
	
	public ExecutionResult ElementIsEnabled(UIElement element);

	public ExecutionResult ElementIsTypeOf(UIElement element, String tagType);

	public ExecutionResult MoveMouseOverElement(UIElement element);

	public ExecutionResult ElementHasFocus(UIElement element);
	
	public ExecutionResult SelectListElement(String value, UIElement element);

}
