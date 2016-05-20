package com.softtek.automation.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.softtek.automation.element.How;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ElementMap {

	// String id();

	How how() default How.XPATH;

	String using();
	
	boolean verify() default false;

}
