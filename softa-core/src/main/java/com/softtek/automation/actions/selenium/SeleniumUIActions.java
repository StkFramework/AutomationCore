/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.actions.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.google.common.base.Strings;
import com.softtek.automation.ErrorType;
import com.softtek.automation.ExecutionContext;
import com.softtek.automation.ExecutionResult;
import com.softtek.automation.ExpressionParserAdapter;
import com.softtek.automation.TestLogger;
import com.softtek.automation.actions.UIActions;
import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.element.How;
import com.softtek.automation.element.UIElement;
import com.softtek.automation.logic.AbstractBusinessCase;
import com.softtek.automation.uiverifications.UIElementsVerification;

/**
 * @author jesus.burquez
 *
 */

public class SeleniumUIActions implements UIActions {

	private TestDriver<WebDriver> testDriver;

	private UIElementsVerification UIElementsVerification;	
	
	private ExecutionContext executionContext;
	
	private ExpressionParserAdapter expressionParserAdapter;
	
	
	
	//public UIElementsVerification getUIElementsVerification() {
	//	return UIElementsVerification;
	//}

	public void setExpressionParserAdapter(ExpressionParserAdapter expressionParserAdapter) {
		this.expressionParserAdapter = expressionParserAdapter;
	}

	public void setUIElementsVerification(UIElementsVerification uIElementsVerification) {
		UIElementsVerification = uIElementsVerification;
	}
		
	@Override
	public void setTestDriver(TestDriver testDriver) {
		this.testDriver = testDriver;
	}

	@Override
	public TestDriver getTestDriver() {
		return this.testDriver;
	}
	
	
	
	public void setExecutionContext(ExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

	/**
	 * This method perform a click on an element
	 * 
	 * @param element The element should be able to perform the click
	 */
	@Override
	public ExecutionResult ClickOnElement(UIElement element) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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

		}else{
			
			executionResult.setMessage(new StringBuilder("Element \"")
					.append(element.getId())
					.append("\" is not displayed for clicking.").toString());
			
		}

		return executionResult;
	}
	
	/**
	 * This method verify if an element have text
	 * 
	 * @param element This element should have the text
	 * @param text The text that should have in the element
	 */
	@Override
	public ExecutionResult ElementHasText(UIElement element, String text) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult() == true) {			
			
			//String textValue = webElement.getText();
			
			String textValue = this.expressionParserAdapter.parseExpression(text, executionContext);
			
			if(textValue == null){
				
				textValue = webElement.getText();
			}
			
			
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

	/**
	 * This method verify if an element contains text
	 * 
	 * @param element This element should contains the text
	 * @param text The text that should contains in the element
	 */
	@Override
	public ExecutionResult ElementContainsText(UIElement element, String text) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult() == true) {
			
			//String textValue = webElement.getText();
			
			String textValue = this.expressionParserAdapter.parseExpression(text, executionContext);
			
			if(textValue == null){
				
				textValue = webElement.getText();
			}
			
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
	
	
	
	
	/**
	 * This method write text in an element
	 * 
	 * @param element The element is where the text should be write
	 * @param text This is the text that will be write in the element
	 */
	@Override
	public ExecutionResult TypeTextOn(UIElement element, String text) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(webElement.isEnabled());

			if (executionResult.isValidResult()) {
				webElement.clear();								
				
				String textValue = this.expressionParserAdapter.parseExpression(text, executionContext);
							
				if(textValue == null){					 
					webElement.sendKeys(text);
				}else{					
					webElement.sendKeys(textValue);
				}				
			} else {
				executionResult.setMessage(new StringBuilder("Element \"")
						.append(element.getId())
						.append("\" is not enabled for type text.").toString());

			}

		}else{
			
			executionResult.setMessage(new StringBuilder("Element \"")
					.append(element.getId())
					.append("\" is not displayed for type text.").toString());
			
		}

		return executionResult;
	}

	/**
	 * This method select value from dropdown element
	 * 
	 * @param element The element is where should select the option
	 * @param text This is the text for the option value
	 */
	@Override
	public ExecutionResult SelectValueFromDropdownElement(UIElement element,
			String text) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);
		
		Select select = new Select(webElement);

			executionResult.setResult(webElement.isEnabled());
			
			if (executionResult.isValidResult()) {
				
				String textValue = this.expressionParserAdapter.parseExpression(text, executionContext);
				
				if(textValue == null){					 
					select.selectByVisibleText(text);
				}else{					
					select.selectByVisibleText(textValue);
				}			
			} else {
				executionResult.setMessage(new StringBuilder("Element \"")
						.append(element.getId())
						.append("\" is not enabled for Select.").toString());

			}
		return executionResult;
	}
	
	/**
	 * This method select value option from dropdown element
	 * 
	 * @param element The element is where should select the option
	 * @param text This is the text for the option value
	 */
	@Override
	public ExecutionResult SelectValueOptionFromDropdownElement(UIElement element,
			int optionNumber) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);
		
		Select select = new Select(webElement);

			executionResult.setResult(webElement.isEnabled());
			
			if (executionResult.isValidResult()) {
				select.selectByIndex(optionNumber);
			//	select.deselectByIndex(1);
			} else {
				executionResult.setMessage(new StringBuilder("Element \"")
						.append(element.getId())
						.append("\" is not enabled for Select.").toString());

			}
		return executionResult;
	}
	
	/**
	 * This method verify if the element is enable to perform an action
	 * 
	 * @param element The element will be verify to check if it's enable
	 */
	@Override
	public ExecutionResult ElementIsEnabled(UIElement element) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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
	
	/**
	 * This method verify the tag of the web element
	 * 
	 * @param element The element should content a tag 
	 * @param tagType This variable contains the tag that will be compare with the element tag
	 */
	@Override
	public ExecutionResult ElementIsTypeOf(UIElement element, String tagType) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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

	/**
	 * This method will move the mouse over an element
	 * 
	 * @param element The element that will have the mouse over
	 */
	@Override
	public ExecutionResult MoveMouseOverElement(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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

	/**
	 * This method verify if the element has the focus of the driver
	 * 
	 * @param element The element should contain the focus
	 */
	@Override
	public ExecutionResult ElementHasFocus(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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

	/**
	 * This method find an element in the UI 
	 * 
	 * @param element The element should exist in the UI
	 */
	private WebElement findWebElement(UIElement element) {
		By by = processBY(element);
		
		WebElement webElement = (new WebDriverWait(testDriver.getDriverInstance(),30))
				.until(ExpectedConditions.presenceOfElementLocated(by));
		
		
		return webElement;

	}
	
	private List<WebElement> findWebElements(UIElement element) {
		By by = processBY(element);
		
		List<WebElement> webElements = (new WebDriverWait(testDriver.getDriverInstance(),30))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		
		
		return webElements;
	}
	
	private By processBY(UIElement element){
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

		return by;
	}
		
	/**
	 * This method wait for the element to be displayed in the UI 
	 * 
	 * @param uiElement The element should exist in the UI 
	 * @param webElement The web element should contain the functions
	 * @param timeOutInSeconds The period of time that the driver wait for the element
	 * @param result The Execution Result object
	 */
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
	
	/**
	 * This method verify if the element is Displayed in the UI 
	 * 
	 * @param uiElement The element should exist in the UI 
	 * @param webElement The web element should contain the functions
	 * @param result The Execution Result object
	 */
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
	
	/**
	 * This method get the string attribute for the element 
	 * 
	 * @param uiElement The element should exist in the UI 
	 * @param attributeType Specify the attribute for the element
	 */
	private String getAttribute(UIElement uiElement, String attributeType) {
		ExecutionResult result = new ExecutionResult();

		WebElement webElement = findWebElement(uiElement);

		isElementDisplayed(uiElement, webElement, result);
		
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

	/**
	 * This method verify if the element is disable to perform an action
	 * 
	 * @param element The element will be verify to check if it's disable
	 */
	@Override
	public ExecutionResult IsDisabled(UIElement element) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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

	/**
	 * This method verify the max lenght from a textbox element
	 * 
	 * @param element The textbox element that will be verify
	 * @param lenght The max lenght that should contains
	 */
	@Override
	public ExecutionResult VerifyMaxLengthText(UIElement element, int length) {

		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {
			executionResult.setResult(webElement.getAttribute("maxlength").equals(
					Integer.toString(length)));
			executionResult.setMessage(executionResult.isValidResult() ? null
					: new StringBuilder().append("Element ")
							.append(element.getId())
							.append(" is NOT matching with the max length")
							.toString());
		}

		return executionResult;
	}

	/**
	 * This method verify if the element is selected or checked
	 * 
	 * @param element The element will be verified if is selected or checked
	 */
	@Override
	public ExecutionResult IsSelected(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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

	/**
	 * This method move the default focus to the next element
	 * 
	 * @param element The element that has the focus
	 */
	@Override
	public ExecutionResult MoveFocusTo(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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
		
	/**
	 * This method select a value from a dropdown
	 * 
	 * @param value The value that will be selected from the dropdown
	 * @param element The dropdown that contains the value that will be selected
	 */
	@Override
	public ExecutionResult SelectValueOnListElement(String value, UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();
		
		boolean getResult = false;
	
		WebElement webElement = findWebElement(element);
	
		isElementDisplayed(element, webElement, executionResult);
		
		Select selectList = new Select(webElement);
		
		List<WebElement> listWebElement = selectList.getOptions();
		
		if(executionResult.isValidResult()){
			for (WebElement singleElement : listWebElement){
				if(singleElement.getText().equals(value)){
					selectList.selectByVisibleText(value);
					getResult = true;
					break;
				}
	
			}
		}
		
			executionResult.setResult(getResult);
			executionResult.setMessage(executionResult.isValidResult() ? null : new StringBuilder()
			.append("Value ")
			.append("\""+value+"\"")
			.append(" NOT exist in dropdown").toString());
		
		return executionResult;	
		
	}

	/**
	 * This method ordered a list of element by ascending or descending
	 * 
	 * @param element The element that contains the list element
	 * @param orderType Is the order type that should be ordered the list
	 */
	@Override
	public ExecutionResult ElementIsOrdered(UIElement element, String orderType) {
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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

	/**
	 * This method verify that an element not exist in the UI
	 * 
	 * @param element The element should not exist in the UI
	 */
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

	/**
	 * This method get the text from a element
	 * 
	 * @param element The element is where the text will be getting
	 */
	@Override
	public ExecutionResult GetText(UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);

		executionResult.setResult(!"input".equals(webElement.getTagName()));
		
		if(executionResult.isValidResult()){
			executionResult.setObjectResult(webElement.getText());
			//TestLogger.INFO(this, "Getting text from element \"" + element.getId() + "\". Text is " + executionResult.getObjectResult().toString());
		}else{			
			executionResult.setMessage(new StringBuilder("Element \"")
					.append(element.getId()).append("\" is <input> type. Can't retrieve text.").toString() );
			TestLogger.ERROR(this, executionResult.getMessage());
		}		
		
		return executionResult;
	}
	
	/**
	 * This method get the value from a element
	 * 
	 * @param element The element is where the value will be getting
	 */
	@Override
	public ExecutionResult GetValue(UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

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
	
	/**
	 * This method select an element from a dropdown
	 * 
	 * @param selectedItem The item or element that will be select
	 * @param element The dropdown element where will be selected the element
	 */
	@Override
	public ExecutionResult SelectElementFromtList(String selectedItem, UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();
		
		WebElement webElement = findWebElement(element);
		
		List<WebElement> getSelectedOption;
		
		WebElement getElement;
				
		boolean getResult = false;
		
		isElementDisplayed(element, webElement, executionResult);
		
		Select selectList = new Select(webElement);
		
		getSelectedOption = selectList.getOptions();
		
		for (WebElement singleElement : getSelectedOption){
			if(singleElement.getText().equals(selectedItem)){
				getElement = singleElement;
				getResult = true;
				break;
			}
		}
		
		if(executionResult.isValidResult()){
			executionResult.setResult(getResult);
			executionResult.setMessage(executionResult.isValidResult() ? null : new StringBuilder()
			.append("Element ")
			.append(selectedItem)
			.append(" NOT exist in list").toString());			
		}
		
		return executionResult;
	}
	
	/**
	 * This method get the selected value from a dropdown.
	 * 
	 * @param element the dropdown element where is the value
	 */
	@Override
	public ExecutionResult GetSelectedValue(UIElement element) {
		ExecutionResult executionResult = new ExecutionResult();
		
		WebElement webElement = findWebElement(element);
		
		WebElement getSelectedOption;
		
		String selectedOption;
		
		isElementDisplayed(element, webElement, executionResult);
		
		Select selectList = new Select(webElement);
		
		getSelectedOption = selectList.getFirstSelectedOption();
		
		selectedOption = getSelectedOption.getText();
		
		if(executionResult.isValidResult()){
			executionResult.setObjectResult(selectedOption);
			executionResult.setResult(!Strings.isNullOrEmpty(selectedOption));
			executionResult.setMessage(executionResult.isValidResult() ? null : new StringBuilder()
			.append("Selected value is null or empty").toString());
		}
		
		return executionResult;
	}
	
	@Override
	public ExecutionResult CountElements(UIElement element) {
		
		ExecutionResult result = new ExecutionResult();

		try{
			
			List<WebElement> elementsList = findWebElements(element);
		
			result.setResult(true);
		
			result.setObjectResult(Integer.valueOf(elementsList.size()));

		} catch (final NoSuchElementException e) {
			result.setResult(false);
			result.setMessage(new StringBuilder().append("Waiting time out: ")
				.append(element.getId())
				.append(" not found.")
				.append("xpath:")
				.append(element.getUsing())
				.toString());
		}
		
		
		return result;
	}
	
	public ExecutionResult GetRowValues(UIElement element) {
		
		ExecutionResult executionResult = new ExecutionResult();

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);
		
		if(element.getHow().equals(How.XPATH)){
			if(element.getUsing().contains("/tbody/")
					&& element.getUsing().contains("/td")){
				
				List<WebElement> tableRows =
						testDriver.getDriverInstance().findElements(
								By.xpath(element.getUsing()));
			
				List<String> rowsResult = new ArrayList<String>();
				
				for(WebElement row : tableRows){
					
					row.findElement(By.xpath(".//td"));
					
				}
			}
		}
		
		return null;
	}

	@Override
	public ExecutionResult VerifyUI(String UIView) throws Exception{		
		return UIElementsVerification.veryfyElements(UIView);
	}

	
	//Overloading Methods
	@Override
	public ExecutionResult ClickOnElement(String xpath, String[] params) {
		return this.ClickOnElement(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult ElementHasText(String xpath, String[] params, String text) {
		return this.ElementHasText(createUIElementFromXpath(xpath, params), text);
	}

	@Override
	public ExecutionResult ElementContainsText(String xpath, String[] params, String text) {
		return this.ElementContainsText(createUIElementFromXpath(xpath, params), text);
	}

	@Override
	public ExecutionResult TypeTextOn(String xpath, String[] params, String text) {
		return this.TypeTextOn(createUIElementFromXpath(xpath, params), text);
	}

	@Override
	public ExecutionResult IsDisabled(String xpath, String[] params) {
		return this.IsDisabled(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult ElementIsEnabled(String xpath, String[] params) {
		return this.ElementIsEnabled(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult VerifyMaxLengthText(String xpath, String[] params, int length) {
		return this.VerifyMaxLengthText(createUIElementFromXpath(xpath, params), length);
	}

	@Override
	public ExecutionResult ElementIsTypeOf(String xpath, String[] params, String tagType) {
		return this.ElementIsTypeOf(createUIElementFromXpath(xpath, params), tagType);
	}

	@Override
	public ExecutionResult SelectListElement(String selectedItem, String xpath, String[] params) {
		return this.SelectElementFromtList(selectedItem, createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult MoveMouseOverElement(String xpath, String[] params) {
		return this.MoveMouseOverElement(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult IsSelected(String xpath, String[] params) {
		return this.IsSelected(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult ElementHasFocus(String xpath, String[] params) {
		return this.ElementHasFocus(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult MoveFocusTo(String xpath, String[] params) {
		return this.MoveFocusTo(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult SelectValueOnList(String value, String xpath, String[] params) {
		return this.SelectValueOnListElement(value, createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult GetSelectedValue(String xpath, String[] params) {
		return this.GetSelectedValue(createUIElementFromXpath(xpath, params));
	}
	
	@Override
	public ExecutionResult ElementNotExist(String xpath, String[] params) {
		return this.ElementNotExist(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult GetText(String xpath, String[] params) {
		return this.GetText(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult GetValue(String xpath, String[] params) {
		// TODO Auto-generated method stub
		return this.GetValue(createUIElementFromXpath(xpath, params));
	}

	@Override
	public ExecutionResult ElementIsOrdered(String xpath, String[] params, String orderType) {
		return this.ElementIsOrdered(createUIElementFromXpath(xpath, params), orderType);
	}
	
	@Override
	public ExecutionResult SelectValueFromDropdownElement(String xpath, String[] params, String value) {
		return this.SelectValueFromDropdownElement(createUIElementFromXpath(xpath, params), value);
	}

	@Override
	public ExecutionResult PutElementValueInCacheContext(UIElement element, String key) {
		
		ExecutionResult result = this.GetValue(element);
		
		if(result.isValidResult()) executionContext.putElementOnCache(key, result.getObjectResult().toString());
		
		
		return result;
	}

	@Override
	public ExecutionResult PutElementValueInVolatileContext(UIElement element, String key) {
		
		ExecutionResult result = this.GetValue(element);
		
		if(result.isValidResult()) executionContext.putElement(key, result.getObjectResult().toString());
		
		
		return result;
	}
	
	
	@Override
	public ExecutionResult PutElementTextInCacheContext(UIElement element, String key) {
		
		ExecutionResult result = this.GetText(element);
		
		if(result.isValidResult()) executionContext.putElementOnCache(key, result.getObjectResult().toString());
		
		
		return result;
	}

	@Override
	public ExecutionResult PutElementTextInVolatileContext(UIElement element, String key) {
		
		ExecutionResult result = this.GetText(element);
		
		if(result.isValidResult()) executionContext.putElement(key, result.getObjectResult().toString());
		
		
		return result;
	}
	
	@Override
	public ExecutionResult PutTextInCacheContext(String text, String key) {
		executionContext.putElementOnCache(key.trim(), text.trim());		
		return new ExecutionResult(true, null);
	}

	@Override
	public ExecutionResult PutTextInVolatileContext(String text, String key) {
		executionContext.putElement(key.trim(), text.trim());			
		return new ExecutionResult(true, null);
	}
	
	@Override
	public ExecutionResult GetTextInCacheContext(String key) {
		ExecutionResult result = new ExecutionResult();		
		String text = executionContext.getElement(key).toString();	
		result.setObjectResult(text);
	//	result.getMessage().toString();
		return result;
		
		
	//	executionContext.getElement(key);
	//	return new ExecutionResult(true, null);
		
	}
	
	@Override
	public String GetStringTextInCacheContext(String key) {
	//	ExecutionResult result = new ExecutionResult();		
		String text = executionContext.getElement(key).toString();	
	//	result.setObjectResult(text);
	//	result.getMessage().toString();
		return text;
		
		
	//	executionContext.getElement(key);
	//	return new ExecutionResult(true, null);
		
	}
	
	@Override
	public ExecutionResult CountElements(String xpath, String[] params) {
		// TODO Auto-generated method stub
		return this.CountElements(createUIElementFromXpath(xpath, params));
	}
	
	@Override
	public ExecutionResult GetColumnValues(String xpath, String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutionResult GetRowValues(String xpath, String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutionResult GetListValues(String xpath, String[] params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getAttribute (String xpath, String[] params, String attribute){
		return this.getAttribute(createUIElementFromXpath(xpath, params), attribute);
	}
	
	private UIElement createUIElementFromXpath(String xpath, String [] args){
		UIElement element = new UIElement();
		
		element.setHow(How.XPATH);
		element.setUsing(processXpath(xpath, args));
		element.setId("Anonym.Element");	
		
		return element;
	}
		
    private String processXpath(String xpath, String [] args)throws IllegalArgumentException {
			//String path = ".//*[@id='carsTable']/tbody/tr[?]/td[?]/a";
			String path = xpath;			
			if(path.contains(":p")){
				if(args != null){
					if(args.length > 0){						
						for(String arg: args){
							path = path.replaceFirst(":p", arg);									
						}
					}else{
						throw new IllegalArgumentException("xpath expresion is specifying some :p arguments but there are not elements in args[].");
					}
				}else{
					throw new IllegalArgumentException("xpath expresion is specifying some :p arguments but args[] is null.");
				}					
			}					
			
			return path;
	}
	
	public static void main(String parms []){
		String xpath = ".//*[@id='carsTable']/tbody/tr[:p]/td[:p]/a";
		String [] args = new String[]{"1" ,"4"};		
		
		System.out.println("xpath -> "+xpath);
		xpath = new SeleniumUIActions().processXpath(xpath, args);
		System.out.println("processed xpath -> "+xpath);
		
	}

	@Override
	public ExecutionResult ExecuteJS(String script, String ... args) {
		ExecutionResult executionResult = new ExecutionResult();
		
		executionResult.setResult(true);
		
		try{
			RemoteWebDriver remoteDriver = (RemoteWebDriver)  testDriver.getDriverInstance();
			
			Object[] elements = new WebElement[args.length];
			
			int i = 0;
			
			for(String arg: args){
				
				UIElement uiElement = new UIElement();
				uiElement.setHow(How.XPATH);
				uiElement.setUsing(arg);
				uiElement.setId("Anonymous.element");
				elements[i] = findWebElement(uiElement);			
			}
			
			remoteDriver.executeScript(script, elements);
		}
		catch(Exception e){
			executionResult.setErrorType(ErrorType.ERROR);
			executionResult.setResult(false);
			executionResult.setMessage("There was an en error when try to perform script " + script + " - "  + e.getMessage() );			
		}
		
		
		return executionResult;
	}
	
	@Override
	public ExecutionResult ClickOnElementWithJS(UIElement uiElement){		
		
		return this.ExecuteJS("arguments[0].click()", uiElement.getUsing());
	}

	
	public ExecutionResult RunBusinessCase(String businesCase) {
		
		Reflections reflections = new Reflections("com.org.test.business");
		
		Set<Class<? extends AbstractBusinessCase>> cases = reflections.getSubTypesOf(AbstractBusinessCase.class);
		
		
		return null;
	}
	
	@Override
	public ExecutionResult ClickOnElementWithActionPerformance(UIElement element){		
		ExecutionResult executionResult = new ExecutionResult();
		Actions action = new Actions(testDriver.getDriverInstance());

		WebElement webElement = findWebElement(element);

		isElementDisplayed(element, webElement, executionResult);

		if (executionResult.isValidResult()) {

			executionResult.setResult(webElement.isEnabled());

			if (executionResult.isValidResult()) {
			//	new Actions(driver.getDriverInstance()).moveToElement(input).click().perform();
				action.moveToElement(webElement).click().perform();
			//	webElement.click()
			} else {
				executionResult.setMessage(new StringBuilder("Element \"")
						.append(element.getId())
						.append("\" is not enabled for clicking.").toString());

			}

		}else{
			
			executionResult.setMessage(new StringBuilder("Element \"")
					.append(element.getId())
					.append("\" is not displayed for clicking.").toString());
			
		}

		return executionResult;
	}
	
		
}
