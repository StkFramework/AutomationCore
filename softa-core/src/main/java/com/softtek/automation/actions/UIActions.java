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
	
	//public ExecutionResult GetRowValues(UIElement element);
	
	public ExecutionResult CountElements(UIElement element);
	
	public ExecutionResult VerifyUI(String UIView)throws Exception;
	
	/*
	 *Overload actions 
	 */

	public ExecutionResult ClickOnElement(String xpath, String[] params);
	
	public ExecutionResult HasText(String xpath, String[] params, String text);
	
	public ExecutionResult ContainsText(String xpath, String[] params, String text);
	
	public ExecutionResult TypeTextOn(String xpath, String[] params, String text);
	
	public ExecutionResult IsDisabled(String xpath, String[] params);
	
	public ExecutionResult IsEnabled(String xpath, String[] params);
	
	public ExecutionResult VerifyMaxLengthText(String xpath, String[] params, int length);
	
	public ExecutionResult IsElementType(String xpath, String[] params, String tagType);
	
	public ExecutionResult SelectListElement(String selectedItem, String xpath, String[] params);
	
	public ExecutionResult MouseOver(String xpath, String[] params);
	
	public ExecutionResult IsSelected(String xpath, String[] params);
	
	public ExecutionResult HasFocus(String xpath, String[] params);
	
	public ExecutionResult MoveFocusTo(String xpath, String[] params);
	
	public ExecutionResult SelectValueOnList(String value, String xpath, String[] params);
	
	public ExecutionResult GetSelectedValue(String xpath, String[] params);
	
	public ExecutionResult GetText(String xpath, String[] params);
	
	public ExecutionResult GetValue(String xpath, String[] params);
	
	public ExecutionResult ElementIsOrdered(String xpath, String[] params, String orderType);
	
	public ExecutionResult PutElementValueInCacheContext(String xpath, String[] params);
	
	public ExecutionResult PutElementValueInVolatileContext(String xpath, String[] params);
	
	public ExecutionResult CountElements(String xpath, String[] params);
	
	public ExecutionResult GetColumnValues(String xpath, String[] params);
	
	public ExecutionResult GetRowValues(String xpath, String[] params);
	
	public ExecutionResult GetListValues(String xpath, String[] params);

	public ExecutionResult ElementNotExist(String xpath, String[] params);	
	
	public ExecutionResult ExecuteJS(String script, String ... args);

		
}
