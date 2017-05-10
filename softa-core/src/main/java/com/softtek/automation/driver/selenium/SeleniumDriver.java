/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.driver.selenium;

import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.softtek.automation.TestLogger;
import com.softtek.automation.driver.TestDriver;

public class SeleniumDriver implements TestDriver<org.openqa.selenium.WebDriver> {

	private static final String FIREFOX = "firefox";
	private static final String IE = "ie";
	private static final String CHROME = "chrome";
	private static final String WEB_DRIVER = "webdriver";
	private static final String GRID_DRIVER = "grid";

	private String browser;
	private String driverType;

	private Properties properties;
	private WebDriver webDriver;

	@Override
	@PostConstruct
	public void setUpDriver() {

		TestLogger.getInstance(this).info("Setting up Testdriver...");

		this.driverType = properties.getProperty("driverType");
		this.browser = properties.getProperty("browser");

		TestLogger.getInstance(this).info("Driver type: " + driverType);
		TestLogger.getInstance(this).info("Browser : " + browser);

		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			if (driverType.equalsIgnoreCase(GRID_DRIVER)) {
				// Configure selenium grid
				if (browser.equals(FIREFOX)) {
					capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability(FirefoxDriver.PROFILE, firefoxConfiguration());
					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				}
				else if (browser.equals(IE)) {
					System.setProperty("webdriver.ie.driver",
							"src/main/resources/ieDriver/IEDriverServer.exe");
					capabilities = DesiredCapabilities.internetExplorer();
					capabilities
							.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
									true);
					capabilities.setVersion("11");
					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				}
				else if (browser.equals(CHROME)) {
					System.setProperty("webdriver.chrome.driver",
							"src/main/resources/ieDriver/IEDriverServer.exe");
					capabilities = DesiredCapabilities.chrome();
					
					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				}
				else {
					throw new IllegalArgumentException("Browser is not supported");
				}
			}
			else {
				// Configure webDriver
				if (browser.equals(FIREFOX)) {
					capabilities.setCapability(FirefoxDriver.PROFILE, firefoxConfiguration());
					webDriver = new FirefoxDriver(capabilities);
				}
				else if (browser.equals(IE)) {
					System.setProperty("webdriver.ie.driver",
							"src/main/resources/drivers/IEDriverServer.exe");
					capabilities = DesiredCapabilities.internetExplorer();
					capabilities
							.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
									true);
					capabilities.setVersion("11");
					webDriver = new InternetExplorerDriver(capabilities);
				}
				else if (browser.equals(CHROME)) {
					ChromeOptions options = new ChromeOptions();
					
					String downloadFilepath = "c:\\tmp";
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);

					options.setExperimentalOption("prefs", chromePrefs);
					capabilities = DesiredCapabilities.chrome();

					capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

					
					
					
					
					options.addArguments("start-maximized");
					options.addArguments("disable-extensions");
					 options.addArguments("--allow-file-access-from-files",
                             "--use-fake-ui-for-media-stream",
                             "--allow-file-access",
                             "--use-file-for-fake-video-capture=c:/video/example2.y4m",
                             "--use-fake-device-for-media-stream");
 
					
					
				//	options.addArguments("allow-file-access-from-files");
				//	options.addArguments("use-fake-device-for-media-stream");
				//	options.addArguments("allow-file-access-from-files");
					//Soptions.addArguments("--use-file-for-fake-video-capture=C:/video/example2.y4m");
					System.setProperty("webdriver.chrome.driver",
							"src/main/resources/drivers/chromedriver.exe");
					
					//ChromeOptions options = new ChromeOptions();
					
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					webDriver = new ChromeDriver(capabilities);
				//	capabilities = DesiredCapabilities.chrome();					
				//	webDriver = new ChromeDriver(capabilities);
				}
				else {
					throw new IllegalArgumentException("Browser is not supported");
				}
			}

		}
		catch (final Exception exception) {
			exception.printStackTrace();
		}

	}

	@Override
	public void refreshDriver() {
		setUpDriver();

	}

	@Override
	public WebDriver getDriverInstance() {

		return this.webDriver;
	}

	private FirefoxProfile firefoxConfiguration() {
		final FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		firefoxProfile.setPreference("browser.download.dir", "c:\\tmp");
		firefoxProfile.setPreference("pdfjs.disabled", true);
		firefoxProfile
				.setPreference(
						"browser.helperApps.neverAsk.saveToDisk",
						"image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,application/x-excel,"
								+ "application/x-msexcel,application/excel,application/pdf, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.openxmlformats-officedocument.presentationml.presentation");
		// New Code
		firefoxProfile
				.setPreference(
						"browser.helperApps.neverAsk.openFile",
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
		firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
		firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);		
		firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
		firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
		firefoxProfile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		
		// profile.setPreference("browser.download.manager.closeWhenDone", false);
		// firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		// profile.setPreference("browser.download.manager.useWindow", false);
		// profile.setPreference("browser.download.useDownloadDir", true);

		return firefoxProfile;
	}

	@Override
	public Properties getProperties() {

		return this.properties;
	}

	@Override
	public void setProperties(Properties props) {
		this.properties = props;

	}

	public static void main(String... args) {

		SeleniumDriver driver = new SeleniumDriver();

		Properties properties = new Properties();
		properties.put("driverType", "webdriver");
		properties.put("browser", "firefox");

		driver.setProperties(properties);

		driver.setUpDriver();

	}

}
