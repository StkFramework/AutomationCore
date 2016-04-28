package com.softtek.automation.actions.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.softtek.automation.ExecutionResult;
import com.softtek.automation.TestLogger;
import com.softtek.automation.actions.UIActions;
import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.element.UIElement;

public class SeleniumUIActions implements UIActions {

	@Autowired
	private TestDriver<WebDriver> testDriver;

	@Override
	public void setTestDriver(TestDriver testDriver) {
		this.testDriver = testDriver;
	}

	@Override
	public TestDriver getTestDriver() {
		return this.testDriver;
	}

	@Override
	public ExecutionResult ClickOnElement(UIElement element) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(webElement.isEnabled());

			if (executionResult.isValidResult()) {
				webElement.click();
			} else {
				executionResult.setMessage(new StringBuilder("Element \"")
						.append(element.getId())
						.append("\" is not enabled for clicking.").toString());

			}

		}

		return executionResult;
	}

	@Override
	public ExecutionResult ElementHasText(UIElement element, String text) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult() == true) {

			String textValue = webElement.getText();
			executionResult.setResult(textValue.equals(text));
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId())
							.append(" doesn't have text \"").append(text)
							.append("\"").append("\nCurrent text is: \"")
							.append(textValue).append("\"").toString());

		}

		return executionResult;
	}

	@Override
	public ExecutionResult ElementContainsText(UIElement element, String text) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult() == true) {

			String textValue = webElement.getText();
			executionResult.setResult(textValue.contains(text));
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId())
							.append(" doesn't contains text \"").append(text)
							.append("\"").append("\nCurrent text is: \"")
							.append(textValue).append("\"").toString());

		}

		return executionResult;
	}

	@Override
	public ExecutionResult TypeTextOnElement(UIElement element, String text) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(webElement.isEnabled());

			if (executionResult.isValidResult()) {
				webElement.clear();
				webElement.sendKeys(text);
			} else {
				executionResult.setMessage(new StringBuilder("Element \"")
						.append(element.getId())
						.append("\" is not enabled for type text.").toString());

			}

		}

		return executionResult;
	}

	@Override
	public ExecutionResult SelectValueFromDropdownElement(UIElement element,
			String text) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		return null;
	}

	@Override
	public ExecutionResult ElementIsEnabled(UIElement element) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(webElement.isEnabled());
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId()).append(" is NOT enabled")
							.toString());
		}
		return executionResult;
	}

	@Override
	public ExecutionResult ElementIsTypeOf(UIElement element, String tagType) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {
			executionResult.setResult(webElement.getTagName().equalsIgnoreCase(
					tagType));
			executionResult
					.setMessage(executionResult.isValidResult() ? null
							: new StringBuilder()
									.append("Element ")
									.append(element.getId())
									.append(" is NOT the tag specified, the correct tag is: ")
									.append(webElement.getTagName()).toString());
		}
		return executionResult;
	}

	@Override
	public ExecutionResult MoveMouseOverElement(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {
			Actions builder = new Actions(testDriver.getDriverInstance());
			Actions hoverOverRequest = builder.moveToElement(webElement);
			hoverOverRequest.perform();
		} else {
			executionResult
					.setMessage(new StringBuilder("Element \"")
							.append(element.getId())
							.append("\" is not Displayed in order to perform the action.")
							.toString());
		}
		return executionResult;
	}

	@Override
	public ExecutionResult ElementHasFocus(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {
			executionResult.setResult(webElement.equals(testDriver
					.getDriverInstance().switchTo().activeElement()));
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId()).append(" is NOT in focus")
							.toString());
		}
		return executionResult;
	}

	/* BE CAREFUL: Dont remove or delete this private methods */

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

		}

	}

	private String getAttribute(UIElement uiElement, WebElement webElement,
			ExecutionResult result, String attributeType) {
		try {

			result.setResult(webElement.isDisplayed());
		} catch (final StaleElementReferenceException | NoSuchElementException e) {
			result.setResult(false);
			result.setMessage(new StringBuilder("Element \"").append("\"")
					.append(uiElement).append("\" is not attached at DOM.")
					.toString());
			result.setError(e);

		}
		return webElement.getAttribute(attributeType.toLowerCase());
	}

	@Override
	public ExecutionResult IsDisable(UIElement element) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(!webElement.isEnabled());
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId()).append(" is NOT disable")
							.toString());

		}

		return executionResult;
	}

	@Override
	public ExecutionResult VerifyMaxLengthText(UIElement element, int length) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {
			executionResult.setResult(webElement.getAttribute("size").equals(
					Integer.toString(length)));
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId())
							.append(" is NOT matching with the max length")
							.toString());
		}

		return executionResult;
	}

	@Override
	public ExecutionResult IsSelected(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {
			executionResult.setResult(webElement.isSelected());
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId())
							.append(" is NOT been selected").toString());

		}

		return executionResult;
	}

	@Override
	public ExecutionResult MoveFocusTo(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element,
				findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if ("input".equals(webElement.getTagName())) {
			webElement.sendKeys("");
		} else {
			new Actions(testDriver.getDriverInstance()).moveToElement(
					webElement).perform();
		}

		if (executionResult.isValidResult()) {
			executionResult.setResult(webElement.equals(testDriver
					.getDriverInstance().switchTo().activeElement()));
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId()).append(" is NOT in focus")
							.toString());
		}

		return executionResult;
	}

	@Override
	public ExecutionResult SelectListElement(String value, UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();
		
		boolean getResult = false;

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);
		
		Select selectList = new Select(webElement);
		
		List<WebElement> listWebElement = selectList.getOptions();
		
		if(executionResult.isValidResult()){
			for (WebElement singleElement : listWebElement){
				if(singleElement.getText().equals(value)){
					getResult = true;
					break;
				}

			}
		}
		
			executionResult.setResult(getResult);
			executionResult.setMessage(executionResult.isValidResult() ? null : new StringBuilder()
			.append("Element ")
			.append(element.getId())
			.append(" NOT exist in dropdown").toString());
		
		return executionResult;	
		
	}

	@Override
	public ExecutionResult ElementIsOrdered(UIElement element, String orderType) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);
		
		List <WebElement> listElements = webElement.findElements(By.xpath("li"));
		
		if(orderType.equalsIgnoreCase("asc")){
			for(int i = 0; i < listElements.size()-1; i++ ){
				executionResult.setResult(listElements.get(i).getText().compareTo(listElements.get(i+1).getText()) <= 0);
				if(!executionResult.isValidResult()) break;
			}
		}
		else if(orderType.equalsIgnoreCase("desc")){
			for(int i = 0; i < listElements.size()-1; i++ ){
				executionResult.setResult(listElements.get(i).getText().compareTo(listElements.get(i+1).getText()) >= 0);
				if(!executionResult.isValidResult()) break;
			}
		}
		
		executionResult.setMessage(
				executionResult.isValidResult() ? null : new StringBuilder()
						.append("The ")
						.append(element.getId())
						.append(" is NOT ordered correctly.").toString());
		return executionResult;
	}

	
	@Override
	public ExecutionResult ElementNotExist(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();
		
		try{
			WebElement webElement = findWebElement(element);
			executionResult.setResult(!webElement.isDisplayed());
		}
		catch (final StaleElementReferenceException | NoSuchElementException e){
			executionResult.setResult(true);
		}
		
		executionResult.setMessage(
				executionResult.isValidResult() ? null : new StringBuilder()
						.append("Element ")
						.append(element.getId())
						.append(" exist in the DOM and the expected is the opposite").toString());
		
		return executionResult;
	}

	@Override
	public ExecutionResult GetText(UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		executionResult.setResult(!"input".equals(webElement.getTagName()));
		
		if(executionResult.isValidResult()){
			executionResult.setObjectResult(webElement.getText());
			TestLogger.INFO(this, "Getting text from element \"" + element.getId() + "\". Text is " + executionResult.getObjectResult().toString());
		}else{			
			executionResult.setMessage(new StringBuilder("Element \"")
					.append(element.getId()).append("\" is <input> type. Can't retrieve text.").toString() );
			TestLogger.ERROR(this, executionResult.getMessage());
		}		
		
		return executionResult;
	}
	
	

	@Override
	public ExecutionResult GetValue(UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		executionResult.setResult("input".equals(webElement.getTagName()));
		
		if(executionResult.isValidResult()){
			executionResult.setObjectResult(webElement.getAttribute("value"));
			TestLogger.INFO(this, "Getting value from element \"" + element.getId() + "\". Value is " + executionResult.getObjectResult().toString());
		}else{			
			executionResult.setMessage(new StringBuilder("Element \"")
					.append(element.getId()).append("\" is not <input> type. Can't retrieve text from \"value\" attribute.").toString() );
			TestLogger.ERROR(this, executionResult.getMessage());
		}		
		
		return executionResult;
	}

}
