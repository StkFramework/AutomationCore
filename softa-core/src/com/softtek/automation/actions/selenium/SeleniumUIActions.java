package com.softtek.automation.actions.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.softtek.automation.ExecutionResult;
import com.softtek.automation.actions.UIActions;
import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.driver.selenium.SeleniumDriver;
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

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(webElement.isEnabled());

			if (executionResult.isValidResult()) {
				webElement.click();
			}
			else {
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

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult() == true) {

			String textValue = webElement.getText();
			executionResult.setResult(textValue.equals(text));
			executionResult.setMessage(
					executionResult.isValidResult() ? null : new StringBuilder()
							.append("Element ")
							.append(element.getId())
							.append(" doesn't have text \"").append(text).append("\"")
							.append("\nCurrent text is: \"")
							.append(textValue).append("\"").toString());

		}

		return executionResult;
	}

	@Override
	public ExecutionResult ElementContainsText(UIElement element, String text) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult() == true) {

			String textValue = webElement.getText();
			executionResult.setResult(textValue.contains(text));
			executionResult.setMessage(
					executionResult.isValidResult() ? null : new StringBuilder()
							.append("Element ")
							.append(element.getId())
							.append(" doesn't contains text \"").append(text).append("\"")
							.append("\nCurrent text is: \"")
							.append(textValue).append("\"").toString());

		}

		return executionResult;
	}

	@Override
	public ExecutionResult TypeTextOnElement(UIElement element, String text) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(webElement.isEnabled());

			if (executionResult.isValidResult()) {
				webElement.clear();
				webElement.sendKeys(text);
			}
			else {
				executionResult.setMessage(new StringBuilder("Element \"")
						.append(element.getId())
						.append("\" is not enabled for type text.").toString());

			}

		}

		return executionResult;
	}
	
	@Override
	public ExecutionResult SelectValueFromDropdownElement(UIElement element, String text) {
		
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);
		
		return null;
	}
	
	/* BE CAREFUL: Dont remove or delete this private methods*/

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

	private WebElement waitForElement(UIElement uiElement, WebElement webElement, Long timeOutInSeconds,
			ExecutionResult result) {

		WebDriverWait wait = new WebDriverWait(testDriver.getDriverInstance(), timeOutInSeconds);

		try {
			wait.until(ExpectedConditions.visibilityOf(webElement));
			// Thread.sleep(ConstantsUtils.TIME_SLEEP_1x);
			Thread.sleep(1000);
		}
		catch (final NoSuchElementException | InterruptedException e) {
			result.setResult(false);
			result.setMessage(new StringBuilder()
					.append("Waiting time out: ")
					.append(uiElement.getId())
					.append(" not found.").toString());
		}

		return webElement;
	}

	private void isElementDisplayed(UIElement uiElement, WebElement webElement, ExecutionResult result) {

		try {

			result.setResult(webElement.isDisplayed());
		}
		catch (final StaleElementReferenceException | NoSuchElementException e) {
			result.setResult(false);
			result.setMessage(new StringBuilder("Element \"").append("\"").append(uiElement).append(
					"\" is not attached at DOM.").toString());
			result.setError(e);

		}

	}

	

	@Override
	public ExecutionResult ElementIsEnabled(UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = waitForElement(element, findWebElement(element), 30L, executionResult);

		isElementDisplayed(element, webElement, executionResult);
		
		if (executionResult.isValidResult()) {

			executionResult.setResult(!webElement.isEnabled());
			executionResult.setMessage(
					executionResult.isValidResult() ? null : new StringBuilder()
							.append("Element ")
							.append(element.getId())
							.append(" is NOT enabled \"").toString());

		}
		
		return executionResult;
	}

}
