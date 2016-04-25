package com.softtek.automation.steps;

import com.softtek.automation.ConstantsUtils;
import com.softtek.automation.ExecutionResult;
import com.softtek.automation.actions.AppActions;

public class AbstractCommonAppSteps extends AbstractSteps {

	protected AppActions AppActions;

	public AppActions getAppActions() {
		return this.AppActions;
	}

	public void setAppActions(AppActions appActions) {
		this.AppActions = appActions;
	}

	protected void openApplication(String name) {
		assertTrue(AppActions.OpenApplication(name));
	}

	protected void openDefault() {
		assertTrue(AppActions.OpenDefault());
	}

	protected void changeToApp(String name) {
	}

	protected void closeApp(String name) {
	}

	protected void closeCurrentApp() {
		assertTrue(AppActions.CloseCurrentApp());
	}


	public void waitForSeconds(String seconds) throws Exception {
		assertTrue(AppActions.WaitForSeconds(seconds));
	}
	
}
