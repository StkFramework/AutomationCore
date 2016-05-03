package com.softtek.automation.uiverifications;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.softtek.automation.ExecutionResult;
import com.softtek.automation.TestLogger;
import com.softtek.automation.anotations.ElementMap;
import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.element.UIElement;

public class SeleniumUIElementsVerifications implements UIElementsVerification{

	private String viewsSource;
	
	private TestDriver<WebDriver> testDriver;
	
	@Override
	public void setViewsSource(String viewsSource) {		
		this.viewsSource = viewsSource;		
	}

	public String getViewsSource(){
		return this.viewsSource;
	}
		
	public TestDriver<WebDriver> getTestDriver() {
		return testDriver;
	}

	public void setTestDriver(TestDriver<WebDriver> testDriver) {
		this.testDriver = testDriver;
	}

	@Override
	public ExecutionResult veryfyElements(String view) throws Exception{

		ExecutionResult executionResult = new ExecutionResult();
		Class<?> clazz = processClassName(view);
		List<UIElement> uiElementsList = getUIElementsForVerifications(clazz);		
		verifyUIElements(uiElementsList,executionResult);
		
		return executionResult;
		
	}		
	
	private void verifyUIElements(List<UIElement> uiElementsList, ExecutionResult executionResult) {		
		
		for(UIElement element: uiElementsList){
			TestLogger.getInstance(this).info("Verifying element \"" + element.getId() 
					+ " by \""+ element.getHow() +"\" using \"" + element.getUsing() + "\"");
			executionResult = new ExecutionResult();
			
			WebElement webElement = waitForElement(element,
					findWebElement(element), 30L, executionResult);

			isElementDisplayed(element, webElement, executionResult);
			
			if(!executionResult.isValidResult()){
				break;
			}
		}
		
	}
	
	private Class<?> processClassName(String className) throws Exception {
		Class<?> clazz;
		try {
			clazz = Class.forName(new StringBuilder(getViewsSource())
							.append(".").append(className).toString());
		}

		catch (ClassNotFoundException e) {
			throw new Exception(new StringBuilder("Failing in UI elements verifications: \"")
					.append(className)
					.append("\" doesn't exists in the views source package : \"")
					.append(getViewsSource())
					.append("\"")
					.toString());
		}
		return clazz;
	}
	
	private List<UIElement> getUIElementsForVerifications(Class<?> clazz)throws Exception{
		
		Field[] fields = clazz.getDeclaredFields();
		
		List<UIElement> uiElementsList = new ArrayList<UIElement>();
		
		for (Field f : fields) {
			try {
					
					f.setAccessible(true);

					ElementMap elementMap = f.getAnnotation(ElementMap.class);

					if(elementMap.verify()){
						UIElement uiElement = new UIElement();

						uiElement.setHow(elementMap.how());
						uiElement.setId(f.getName());
						uiElement.setUsing(elementMap.using());
						uiElementsList.add(uiElement);
					}
			}
			catch (Exception e) {
				throw new Exception("The field [" + f.getName() + "] in class ["
						+ clazz.getCanonicalName()
						+ "] can't be processed.", e);
			}
		}
		return uiElementsList;
	}	
	
	private WebElement findWebElement(UIElement element) {
		By by = null;

		switch (element.getHow()) {
		case XPATH:
			by = By.xpath(element.getUsing());
			break;

		case CLASS:
			by = By.className(element.getUsing());
			break;

		case ID:
			by = By.id(element.getUsing());
			break;

		case TAG:
			by = By.tagName(element.getUsing());
			break;

		case NAME:
			by = By.name(element.getUsing());
			break;

		default:
			by = By.xpath(element.getUsing());
			break;
		}

		return testDriver.getDriverInstance().findElement(by);

	}

	private WebElement waitForElement(UIElement uiElement,
			WebElement webElement, Long timeOutInSeconds, ExecutionResult result) {

		WebDriverWait wait = new WebDriverWait(testDriver.getDriverInstance(),
				timeOutInSeconds);

		try {
			wait.until(ExpectedConditions.visibilityOf(webElement));
			// Thread.sleep(ConstantsUtils.TIME_SLEEP_1x);
			Thread.sleep(1000);
		} catch (final NoSuchElementException | InterruptedException e) {
			result.setResult(false);
			result.setMessage(new StringBuilder().append("Waiting time out: ")
					.append(uiElement.getId()).append(" not found.").toString());
		}

		return webElement;
	}

	private void isElementDisplayed(UIElement uiElement, WebElement webElement,
			ExecutionResult result) {

		try {

			result.setResult(webElement.isDisplayed());
		} catch (final StaleElementReferenceException | NoSuchElementException e) {
			result.setResult(false);
			result.setMessage(new StringBuilder("Element \"").append("\"")
					.append(uiElement).append("\" is not attached at DOM.")
					.toString());
			result.setError(e);
			TestLogger.ERROR(this, result.getMessage() + "Excpetion message is: " + e.getMessage());

		}

	}
}
