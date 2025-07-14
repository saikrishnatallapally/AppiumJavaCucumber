package com.challengeapp.hooks;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.challengeapp.capabilities.AppiumDriverSetUp;
import com.challengeapp.capabilities.DriverManager;
import com.challengeapp.utils.PropertyReader;

public class Hooks {

	private static final Logger logger = LogManager.getLogger(Hooks.class);

	@Before
	public void beforeScenario(Scenario scenario) {
		PropertyReader reader = PropertyReader.getInstance();
		logger.info("Starting scenario: " + scenario.getName());

		String platform;

		platform = System.getProperty("platform", "android");
		if (platform == null || platform.isEmpty()) {
			platform = reader.getProperty("platform");
		}

		AppiumDriver driver = new AppiumDriverSetUp().initializeDriver(platform);
		DriverManager.setDriver(driver);
	}

	@After
	public void afterScenario(Scenario scenario) {

		if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Screenshot");
				logger.info("scenario has failed");
				ExtentCucumberAdapter.addTestStepLog("scenario has failed");
			} 
		DriverManager.quitDriver();
		}
	}
