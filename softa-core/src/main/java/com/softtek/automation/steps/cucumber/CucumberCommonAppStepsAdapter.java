package com.softtek.automation.steps.cucumber;

import com.softtek.automation.ConstantsUtils;
import com.softtek.automation.steps.AbstractCommonAppSteps;

import cucumber.api.java.en.When;

public class CucumberCommonAppStepsAdapter extends AbstractCommonAppSteps {

	@When("^(?i:I open application) '(.+)'$")
	public void i_open_application(String appName) {
		this.openApplication(appName);
	}

	@When("^(?i:I open default application)$")
	public void i_open_default_application() {
		this.openDefault();
	}

	
	@When("^(?i:I wait) '([0-9]*)' (?i:seconds)$")
	public void i_wait_seconds(final String seconds) throws Exception {
		this.waitForSeconds(seconds);
	}
	
	@When("^(?i:I close current application)$")
	public void i_close_current_application() {
		this.closeCurrentApp();;
	}
}
