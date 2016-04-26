package com.softtek.automation.steps;

import com.softtek.automation.actions.UIActions;
import com.softtek.automation.element.UIElement;
import com.softtek.automation.element.UIElementFactory;

public abstract class AbstractCommonUISteps extends AbstractSteps {

	public UIActions UIActions; // Los UIActions actions son definidos como beans y tendran como propiedade para

	protected UIElementFactory UIElementFactory;

	public UIElementFactory getUIElementFactory() {
		return UIElementFactory;
	}

	public void setUIElementFactory(UIElementFactory uIElementFactory) {
		UIElementFactory = uIElementFactory;
	}

	public UIActions getUIActions() {
		return UIActions;
	}

	public void setUIActions(UIActions uIActions) {
		UIActions = uIActions;
	}

	protected void clickOnElement(UIElement element) {
		assertTrue(UIActions.ClickOnElement(element));
	}

	protected void elementHasText(UIElement element, String text) {
		assertTrue(UIActions.ElementHasText(element, text));
	}

	protected void elementContainsText(UIElement element, String text) {
		assertTrue(UIActions.ElementContainsText(element, text));
	}

	protected void typeTextOnElement(UIElement element, String text) {
		assertTrue(UIActions.TypeTextOnElement(element, text));
	}
	
	protected void elementIsEnabled(UIElement element) {
		assertTrue(UIActions.ElementIsEnabled(element));
	}
	
	protected void elementIsTypeOf(UIElement element, String tagType) {
		assertTrue(UIActions.ElementIsTypeOf(element, tagType));
	}
	
	protected void moveMouseOverElement(UIElement element) {
		assertTrue(UIActions.MoveMouseOverElement(element));
	}
	
	protected void elementHasFocus(UIElement element) {
		assertTrue(UIActions.ElementHasFocus(element));
	}
		
}
