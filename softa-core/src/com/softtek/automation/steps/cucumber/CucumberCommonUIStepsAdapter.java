package com.softtek.automation.steps.cucumber;

import com.softtek.automation.steps.AbstractCommonUISteps;

import cucumber.api.java.en.When;

public class CucumberCommonUIStepsAdapter extends AbstractCommonUISteps {

	@When("^(?i:I click on) '(.+)'$")
	public void i_click_on_element(String element) {
		// this.clickOnElement(UIElementFactory.createElement(element));
		this.clickOnElement(null);
	}

	@When("^(?i:Element) '(.+)' (?i:has text) '(.+)'$")
	public void element_has_text(String element, String text) {
		// this.elementHasText(UIElementFactory.createElement(element), text);
	}

	@When("^(?i:Element) '(.+)' (?i:contains text) '(.+)'$")
	public void element_contains_text(String element, String text) {
		// this.elementContainsText(this.UIElementFactory.createElement(element), text);
	}

}
