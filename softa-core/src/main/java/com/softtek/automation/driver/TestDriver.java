package com.softtek.automation.driver;

import java.util.Properties;

public interface TestDriver<T> {

	void setUpDriver();

	void refreshDriver();

	T getDriverInstance();

	Properties getProperties();

	void setProperties(Properties props);

}
