/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.structure.mapping.selenium;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.element.UIElement;
import com.softtek.automation.element.UIElementFactory;
import com.softtek.automation.structure.AbstractStructure;
import com.softtek.automation.structure.mapping.ElementLink;
import com.softtek.automation.structure.mapping.StructureMapperFactory;


@Component
public class SeleniumStructureMapperFactory implements StructureMapperFactory {

	TestDriver<WebDriver> testDriver;
	UIElementFactory UIElementFactory;

	public void setTestDriver(TestDriver<WebDriver> testDriver) {
		this.testDriver = testDriver;
	}
	
	public void setUIElementFactory(UIElementFactory uIElementFactory) {
		UIElementFactory = uIElementFactory;
	}



	@Override
	public Object getMappedObject(Class<? extends AbstractStructure> clazz) throws Exception {
		
		WebDriver driver = testDriver.getDriverInstance();
		
		Object mappedInstance = clazz.newInstance();
		
		Field[] fields = mappedInstance.getClass().getDeclaredFields();
		
		processSimpleFields(fields, mappedInstance, driver, this.UIElementFactory);
		//processComplexFields(fields, mappedInstance, driver, this.UIElementFactory); Not implemented yet
		
		
		return mappedInstance;
	}
	
	

	private void processSimpleFields(Field[] fields, Object mappedInstance, WebDriver driver, UIElementFactory elementFactory) throws Exception{
		
		for(Field f : fields){
			
			f.setAccessible(true);
			
			ElementLink elementLink = f.getAnnotation(ElementLink.class);
			
			if(elementLink == null){
				continue;
			}else{
				
				String uiElementReference = elementLink.uiElement();
				UIElement uiElement= UIElementFactory.createElement(uiElementReference);
				WebElement webElement = findWebElement(uiElement);
				
				if("input".equals(webElement.getTagName())){
					f.set(mappedInstance, webElement.getAttribute("value"));
				}else{
					f.set(mappedInstance, webElement.getText());
				}				
			}
			
		}
		
	}

	private void processComplexFields(Field[] fields, Object mappedInstance, WebDriver driver,
			com.softtek.automation.element.UIElementFactory uiElementFactory) {
	
		
	}
	
	private WebElement findWebElement(UIElement element) {
		By by = processBY(element);
		return testDriver.getDriverInstance().findElement(by);

	}
	
	private List<WebElement> findWebElements(UIElement element) {
		By by = processBY(element);
		return testDriver.getDriverInstance().findElements(by);
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

}
