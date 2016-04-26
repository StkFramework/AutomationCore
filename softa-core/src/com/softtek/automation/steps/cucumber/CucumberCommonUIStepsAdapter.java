package com.softtek.automation.steps.cucumber;

import com.softtek.automation.steps.AbstractCommonUISteps;

import cucumber.api.java.en.When;

public class CucumberCommonUIStepsAdapter extends AbstractCommonUISteps {

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
		this.typeTextOnElement(this.UIElementFactory.createElement(element), text);		
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

}
