package com.softtek.automation.actions.selenium;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.softtek.automation.Application;
import com.softtek.automation.ApplicationsSet;
import com.softtek.automation.ExecutionResult;
import com.softtek.automation.actions.AppActions;
import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.driver.selenium.SeleniumDriver;

public class SeleniumAppActions implements AppActions {

	@Autowired
	private TestDriver<SeleniumDriver> testDriver;

	private ApplicationsSet applicationsSet;

	private Application application;

	@Override
	public Application getApplication() {
		return this.application;
	}

	@Override
	public void setApplication(Application application) {
		this.application = application;

	}

	@Override
	public void setApplicationsSet(ApplicationsSet applicationsSet) {
		this.applicationsSet = applicationsSet;

	}

	@Override
	public ApplicationsSet getApplicationsSet() {

		return this.applicationsSet;
	}

	@Override
	public void setTestDriver(TestDriver testDriver) {
		this.testDriver = testDriver;
	}

	@Override
	public TestDriver getTestDriver() {
		return this.testDriver;
	}

	@Override
	public ExecutionResult OpenApplication(String application) {

		// ExecutionResult result = null;

		System.out.println("Processing OpenApplication ...");

		List<Application> applicationsList = applicationsSet.getApplications();
		for (Application app : applicationsList) {

			System.out.println("app.getName-> " + app.getName());
			if (application.equals(app.getName())) {
				testDriver.getDriverInstance().getDriverInstance().manage().window().maximize();
				testDriver.getDriverInstance().getDriverInstance().manage().deleteAllCookies();

				System.out.println("url-> " + app.getProperties().get("url.").toString() + app.getEnvironment());

				testDriver.getDriverInstance().getDriverInstance().get(
						app.getProperties().get("url.").toString() + app.getEnvironment());
			}

		}

		return new ExecutionResult(true, "");
	}

	@Override
	public ExecutionResult OpenDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutionResult ChangeToApp(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutionResult CloseApp(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutionResult CloseCurrentApp(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
