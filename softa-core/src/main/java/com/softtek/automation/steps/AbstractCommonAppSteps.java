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
	
	public void moveToFrame(String name) throws Exception {
		assertTrue(AppActions.MoveToFrame(name));

	}
	
	public void moveToIdFrame(String id) throws Exception {
		assertTrue(AppActions.MoveToIdFrame(id));

	}

	public void moveToDefaultContent() throws Exception {
		assertTrue(AppActions.MoveToDefaultContent());

	}

	public void moveToWindow(String index) throws Exception {
		assertTrue(AppActions.MoveToWindow(index));
	}
	
	public String getTextFromAlert() throws Exception {
		return AppActions.GetTextFromAlert().getMessage();
	}
	
	
}
