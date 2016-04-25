package com.softtek.automation.actions;

import com.softtek.automation.Application;
import com.softtek.automation.ApplicationsSet;
import com.softtek.automation.ExecutionResult;

public interface AppActions extends Actions {

	Application getApplication();

	void setApplication(Application application);

	void setApplicationsSet(ApplicationsSet applicationsSet);

	ApplicationsSet getApplicationsSet();

	ExecutionResult OpenApplication(String name);

	ExecutionResult OpenDefault();

	ExecutionResult ChangeToApp(String name);

	ExecutionResult CloseApp(String name);

	ExecutionResult CloseCurrentApp();
	
	ExecutionResult WaitForSeconds(String seconds) throws Exception;

}
