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
	
	public ExecutionResult SelectValueOnListElement(String value, UIElement element);

	public ExecutionResult ElementIsOrdered(UIElement element, String orderType);
	
	public ExecutionResult GetText(UIElement element);
	
	public ExecutionResult GetValue(UIElement element);	

	public ExecutionResult ElementNotExist(UIElement element);
	
	public ExecutionResult SelecElementFromtList(String selectedItem, UIElement element);
	
	public ExecutionResult GetSelectedValue(UIElement element);

}
