/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
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

	protected void typeTextOn(UIElement element, String text) {
		assertTrue(UIActions.TypeTextOn(element, text));
	}
	
	protected void isDisable(UIElement element){
		assertTrue(UIActions.IsDisabled(element));
	}
	
	protected void VerifyMaxLengthText(UIElement element, int length){
		assertTrue(UIActions.VerifyMaxLengthText(element, length));
	}
	
	protected void IsSelected(UIElement element){
		assertTrue(UIActions.IsSelected(element));
	}
	
	protected void MoveFocusTo(UIElement element){
		assertTrue(UIActions.MoveFocusTo(element));
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
	
	protected void selectListElement(String value, UIElement element){
		assertTrue(UIActions.SelectElementFromtList(value, element));
	}
	
	protected void elementIsOrdered(UIElement element, String orderType) {
		assertTrue(UIActions.ElementIsOrdered(element, orderType));

	}
	
	protected void elementNotExist(UIElement element) {
		assertTrue(UIActions.ElementNotExist(element));
	}
	
	protected void getSelectedValue(UIElement element){
		assertTrue(UIActions.GetSelectedValue(element));
	}
	
	protected void selectValueOnListElement(String value, UIElement element){
		assertTrue(UIActions.SelectValueOnListElement(value, element));
	}
	
	protected void verifyUI(String UIView) throws Exception{
		assertTrue(UIActions.VerifyUI(UIView));
	}
	
	protected void selectValueFromDropdownElement(UIElement element, String optionNumber){
		
		assertTrue(UIActions.SelectValueFromDropdownElement(element, optionNumber));		
	}
		
	protected void selectValueOptionFromDropdownElement(UIElement element, int value){
		
		assertTrue(UIActions.SelectValueOptionFromDropdownElement(element, value));		
	}

	protected void putElementValueInCacheContext(UIElement element, String key){
		
		assertTrue(UIActions.PutElementValueInCacheContext(element, key));
	}	
	
	protected void putElementTextInCacheContext(UIElement element, String key){
		
		assertTrue(UIActions.PutElementTextInCacheContext(element, key));
	}
	
	protected void putElementValueInVolatileContext(UIElement element, String key){
		
		assertTrue(UIActions.PutElementValueInVolatileContext(element, key));
	}
	
	protected void putElementTextInVolatileContext(UIElement element, String key){
		
		assertTrue(UIActions.PutElementTextInVolatileContext(element, key));
	}
	
	
	protected void putTextInCacheContext(String key, String text){
		
		assertTrue(UIActions.PutTextInCacheContext(text,key));
	}
	
	protected void putTextInVolatileContext(String key, String text){
		
		assertTrue(UIActions.PutTextInVolatileContext(text,key));
	}
	
	protected String getTextInCacheContext(String key){
		
		return UIActions.GetTextInCacheContext(key).toString();
	}
	
	protected String getStringTextInCacheContext(String key){
		
		return UIActions.GetStringTextInCacheContext(key).toString();
	}
	
	protected void clickOnElementWithJS(UIElement uiElement) {
		assertTrue(UIActions.ClickOnElementWithJS(uiElement));
		
	}
	
	protected void clickOnElementWithActionPerformance(UIElement element) {
		assertTrue(UIActions.ClickOnElementWithActionPerformance(element));
	}
	
}
