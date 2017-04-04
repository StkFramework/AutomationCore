/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.steps.cucumber;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.softtek.automation.element.UIElement;
import com.softtek.automation.logic.AbstractBusinessCase;
import com.softtek.automation.steps.AbstractCommonUISteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberCommonUIStepsAdapter extends AbstractCommonUISteps implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

	@When("^(?i:I click on) '(.+)'$")
	public void i_click_on_element(String element) throws Exception {		
		this.clickOnElement(UIElementFactory.createElement(element));		
	}

	@When("^(?i:Element) '(.+)' (?i:has text) '(.+)'$")
	public void element_has_text(String element, String text) throws Exception {
		this.elementHasText(UIElementFactory.createElement(element), text);		
	}

	@When("^(?i:Element) '(.+)' (?i:contains text) '(.+)'$")
	public void element_contains_text(String element, String text) throws Exception {
		this.elementContainsText(this.UIElementFactory.createElement(element), text);
	}	
	
	@When("^(?i:I type text) '(.+)' (?i:on element) '(.+)'$")
	public void i_type_text_on(String text,String element) throws Exception {		
		this.typeTextOn(this.UIElementFactory.createElement(element), text);		
	}
	
	@When("^(?i:Element) '(.+)' (?i:is disable)$")
	public void element_is_disable(String element) throws Exception {		
		this.isDisable(UIElementFactory.createElement(element));		
	}
	
	@Then("^(?i:Verify) '(.+)' (?i:has max length) '(.+)'$")
	public void verify_has_max_length(String element, int length) throws Exception {		
		this.VerifyMaxLengthText(UIElementFactory.createElement(element), length);		
	}
	
	@Then("^(?i:Verify element) '(.+)' (?i:is selected)$")
	public void verify_element_is_selected(String element) throws Exception {		
		this.IsSelected(UIElementFactory.createElement(element));		
	}
	
	@And("^(?i:Move focus to) '(.+)'$")
	public void move_focus_to(String element) throws Exception {		
		this.MoveFocusTo(UIElementFactory.createElement(element));		
	}
	
	@When("^(?i:Element) '(.+)' (?i:is enabled)$")
	public void element_is_enabled(String element) throws Exception {		
		this.elementIsEnabled(this.UIElementFactory.createElement(element));		
	}
	
	@When("^(?i:Element) '(.+)' (?i:is type:) '(.+)'$")
	public void element_is_type_of(String element, String tagType) throws Exception {		
		this.elementIsTypeOf(this.UIElementFactory.createElement(element), tagType);		
	}
	
	@When("^(?i:I move the mouse over the element) '(.+)'$")
	public void move_mouse_over_element(String element) throws Exception {		
		this.moveMouseOverElement(this.UIElementFactory.createElement(element));		
	}
	
	@When("^(?i:Element) '(.+)' (?i:has the focus)$")
	public void element_has_focus(String element) throws Exception {		
		this.elementHasFocus(this.UIElementFactory.createElement(element));		
	}
	
	@And("^(?i:Select element) '(.+)' (?i:from list) '(.+)'$")
	public void select_element_from_list(String element, String list) throws Exception{
		this.selectListElement(element, this.UIElementFactory.createElement(list));
	}
	
	@When("^(?i:Element) '(.+)' (?i:list is ordered) '(.+)'$")
	public void element_is_ordered(String element, String orderType) throws Exception {		
		this.elementIsOrdered(this.UIElementFactory.createElement(element), orderType);		

	}
	
	@When("^(?i:Element) '(.+)' (?i:NOT exist)$")
	public void element_not_exist(String element) throws Exception {		
		this.elementNotExist(this.UIElementFactory.createElement(element));		
	}
	
	@Then("^(?i:Select value) '(.+)' (?i:from list) '(.+)'$")
	public void select_value_from_list(String value, String element) throws Exception{
		this.selectValueOnListElement(value, this.UIElementFactory.createElement(element));
	}
	
	@And("^(?i:Get selected value from) '(.+)' (?i:dropdown list)$")
	public void get_selected_value_from_dropdown_list(String element) throws Exception{
		this.getSelectedValue(this.UIElementFactory.createElement(element));
	}

	@When("^(?i:I verify UI view) '(.+)'$")
	public void i_verify_ui(String UIView) throws Exception {		
		this.verifyUI(UIView);		
	}
	
	@Then("^(?i:Select value) '(.+)' (?i:from dropdown) '(.+)'$")
	public void select_value_from_dropdown_element(String value, String element) throws Exception{
		this.selectValueFromDropdownElement(this.UIElementFactory.createElement(element), value);
	}	
	
	@Then("^(?i:Select the) '(.+)' option (?i:from dropdown) '(.+)'$")
	public void select_value_option_from_dropdown_element(int value, String element) throws Exception{
		System.out.println(value);
		this.selectValueOptionFromDropdownElement(this.UIElementFactory.createElement(element), value);
	}	
	
	@Then("^(?i:Put element) '(.+)' (?i:value with key) '(.+)' (?i:in cache context)$")
	public void put_element_value_in_cache_context(String key, String element) throws Exception{
		this.putElementValueInCacheContext(this.UIElementFactory.createElement(element), key);
	}
	
	@Then("^(?i:Put element) '(.+)' (?i:value with key) '(.+)' (?i:in volatile context)$")
	public void put_element_value_in_volatile_context(String key, String element) throws Exception{
		this.putElementValueInVolatileContext(this.UIElementFactory.createElement(element), key);
	}
	
	@Then("^(?i:Put element) '(.+)' (?i:text with key) '(.+)' (?i:in cache context)$")
	public void put_element_text_in_cache_context(String element,String key) throws Exception{
		this.putElementTextInCacheContext(this.UIElementFactory.createElement(element), key);
	}
	
	@Then("^(?i:Put element) '(.+)' (?i:text with key) '(.+)' (?i:in volatile context)$")
	public void put_element_text_in_volatile_context(String element, String key) throws Exception{
		this.putElementTextInVolatileContext(this.UIElementFactory.createElement(element), key);
	}
	
	@Then("^(?i:Put text) '(.+)' (?i:with key) '(.+)' (?i:in cache context)$")
	public void put_text_in_cache_context(String text, String key) throws Exception{
		this.putTextInCacheContext(key,text);
	}
	
	@Then("^(?i:Put text) '(.+)' (?i:with key) '(.+)' (?i:in volatile context)$")
	public void put_text_in_volatile_context(String text, String key) throws Exception{
		this.putTextInVolatileContext(key,text);
	}
	
	@Then("^(?i:Get text) (?i:with key) '(.+)' (?i:in cache context)$")
	public void get_text_in_cache_context(String key) throws Exception{
		this.getTextInCacheContext(key);
	}
	
	@Then("^(?i:I type text with key) '(.+)' (?i:on element) '(.+)' (?i:using cache context)$")
	public void i_type_text_with_key_on_element_using_cache_context(String key, String element) throws Exception{
		this.typeTextOn(this.UIElementFactory.createElement(element), this.getStringTextInCacheContext(key).toString());
	}
	
	

	@When("^(?i:I click on) '(.+)' (?i:with JS)$")
	public void i_click_on_element_with_JS(String element) throws Exception {		
		this.clickOnElementWithJS(UIElementFactory.createElement(element));		
	}

	
	@When("^(?i:Run business case) '(.+)'$")
	public void run_business_case(String businessCase) throws Exception {		
		if(applicationContext.containsBean(businessCase)){
			
			Object o = applicationContext.getBean(businessCase);
			
			if(o instanceof AbstractBusinessCase){
				
				AbstractBusinessCase bc = (AbstractBusinessCase) o;
				bc.run(this.ExecutionContext);
				
			}else{
				assertTrue(false, "ERROR: \""+ businessCase +"\" is not an instance of AbstractBusinessCase");
			}
			
		}	else{			
			assertTrue(false, "ERROR: BusinessCase \"" + businessCase + "\" doesn't exist in application context");
		}	
	}
	
	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.applicationContext = appContext;
		
	}
	
	@When("^(?i:I click on) '(.+)' (?i:with Action Performance)$")
	public void i_click_on_element_with_action_performance(String element) throws Exception {		
		this.clickOnElementWithActionPerformance(UIElementFactory.createElement(element));		
	}


	
}
