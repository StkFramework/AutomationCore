package com.softtek.automation.uiverifications;

import org.openqa.selenium.WebDriver;

import com.softtek.automation.ExecutionResult;
import com.softtek.automation.driver.TestDriver;

public interface UIElementsVerification {
	
	void setTestDriver(TestDriver<WebDriver> driver);
	
	void setViewsSource(String source);
	ExecutionResult veryfyElements(String view) throws Exception;
}
