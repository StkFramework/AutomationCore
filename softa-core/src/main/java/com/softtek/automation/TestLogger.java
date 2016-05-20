package com.softtek.automation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {

	private static Logger LOGGER;

	public static Logger getInstance(Object object){		
		return LoggerFactory.getLogger(object.getClass()); 
	}
	
	public static void DEBUG(Object object, String message) {
		LOGGER = LoggerFactory.getLogger(object.getClass());
		
		LOGGER.debug(message);
	}
	
	public static void ERROR(Object object, String message) {
		LOGGER = LoggerFactory.getLogger(object.getClass());
		LOGGER.error(message);
	}
	
	public static void INFO(Object object, String message) {
		LOGGER = LoggerFactory.getLogger(object.getClass());
		LOGGER.info(message);
	}
}
