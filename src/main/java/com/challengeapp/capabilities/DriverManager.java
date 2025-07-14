package com.challengeapp.capabilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

	private static final Logger logger = LogManager.getLogger(DriverManager.class);
	private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

	private DriverManager() {
	}

	public static void setDriver(AppiumDriver driver) {
		driverThreadLocal.set(driver);
	}

	public static AppiumDriver getDriver() {

		AppiumDriver driver = driverThreadLocal.get();
		if (driver == null) {
			throw new IllegalStateException("Driver not initialized for current thread. Call setDriver() first.");
		}
		return driver;
	}

	public static void quitDriver() {
		AppiumDriver driver = driverThreadLocal.get();
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				logger.info("Error: " + e.getMessage());
			} finally {
				driverThreadLocal.remove();
			}
		}
	}

}