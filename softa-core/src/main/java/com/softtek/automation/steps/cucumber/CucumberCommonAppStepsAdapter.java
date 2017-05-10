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

import org.junit.Assert;
import org.openqa.selenium.Alert;

import com.softtek.automation.ConstantsUtils;
import com.softtek.automation.steps.AbstractCommonAppSteps;

import cucumber.api.java.en.Then;
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
	
	@When("^(?i:I move to frame) '(.+)'$")
	public void i_move_to_frame(String name)throws Exception {
		this.moveToFrame(name);
	}
	
	@When("^(?i:I move to id frame) '(.+)'$")
	public void i_move_to_id_frame(String id)throws Exception {
		this.moveToIdFrame(id);
	}
	
	@When("^(?i:I move to default content)$")
	public void i_move_to_default_content() throws Exception {
		this.moveToDefaultContent();
	}
	
	@When("^(?i:I move to window) '(.+)'$")
	public void i_move_to_window(String index)throws Exception {
		this.moveToWindow(index);
	}
	
	
	@When("^I verify the popup window text '(.+)' and select '(.+)'$")
	public void i_verify_the_popup_window_text_and_select(String expectedAlertMsg, String btnAction) throws Throwable {
		String alertMSg = this.getTextFromAlert();
		System.out.println(alertMSg);
	}

	
}
