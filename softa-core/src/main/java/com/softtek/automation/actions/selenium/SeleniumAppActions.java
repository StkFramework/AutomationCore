package com.softtek.automation.actions.selenium;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.softtek.automation.Application;
import com.softtek.automation.ApplicationsSet;
import com.softtek.automation.ConstantsUtils;
import com.softtek.automation.ExecutionResult;
import com.softtek.automation.TestLogger;
import com.softtek.automation.actions.AppActions;
import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.driver.selenium.SeleniumDriver;

public class SeleniumAppActions implements AppActions {

	@Autowired
	private TestDriver<WebDriver> testDriver;

	private ApplicationsSet applicationsSet;

	private Application currentApplication;

	@Override
	public Application getApplication() {
		return this.currentApplication;
	}

	@Override
	public void setApplication(Application application) {
		this.currentApplication = application;

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
	public TestDriver<WebDriver> getTestDriver() {
		return this.testDriver;
	}

	@Override
	public ExecutionResult OpenApplication(String application) {

		ExecutionResult result = new ExecutionResult();
		boolean applicationFound = false;
		
		TestLogger.getInstance(this).info(application);

		List<Application> applicationsList = applicationsSet.getApplications();
				
		for (Application app : applicationsList) {			
			if (application.equals(app.getName())) {
				
							
				
				if(((RemoteWebDriver)testDriver.getDriverInstance()).getSessionId() == null){
					
					testDriver.setUpDriver();
				}
				 
				
				TestLogger.getInstance(this).info("Using browser with sessionID -> " + ((RemoteWebDriver)testDriver.getDriverInstance()).getSessionId());
				
				testDriver.getDriverInstance().manage().window().maximize();
				testDriver.getDriverInstance().manage().deleteAllCookies();

				TestLogger.getInstance(this).info("application.environment = " + app.getEnvironment());
				TestLogger.getInstance(this).info("application.properties = " + app.getProperties());

				testDriver.getDriverInstance().get(app.getProperties().get("url."+ app.getEnvironment()).toString());		
				
				//testDriver.getDriverInstance().navigate().to(app.getProperties().get("url."+ app.getEnvironment()).toString());
			
				applicationFound = true;
				break;
			}
		}

		if(applicationFound == false){
			result.setResult(false);
			result.setMessage("Aplication \"" + application +"\" can't be opened. There isn't a definition.");
			TestLogger.getInstance(this).error(result.getMessage());
		}
		
		return result;
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
		testDriver.getDriverInstance().close();
		return new ExecutionResult(true,null);
	}

	@Override
	public ExecutionResult CloseCurrentApp() {
		//testDriver.getDriverInstance().close();
		
		TestLogger.getInstance(this).info("Closing browser with sessionID -> " + ((RemoteWebDriver)testDriver.getDriverInstance()).getSessionId());
		testDriver.getDriverInstance().quit();
		
		return new ExecutionResult(true,null);
	}

	@Override
	public ExecutionResult WaitForSeconds(String seconds) throws Exception {
		Thread.sleep( Integer.parseInt(seconds) * ConstantsUtils.TIME_SLEEP_1x );
		return new ExecutionResult(true,null);
	}
	
	//Adding this method just to verify UIActions Complex -- You can deleted any time
	@Override
	public ExecutionResult MoveToUrl(String url) throws Exception {
		ExecutionResult result = new ExecutionResult();
		testDriver.getDriverInstance().navigate().to(url);
		WaitForSeconds("3");
		return result;
	}

	
}
