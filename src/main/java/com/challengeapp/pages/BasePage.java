package com.challengeapp.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.LogManager;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.challengeapp.utils.PropertyReader;

import java.time.Duration;

public abstract class BasePage {

	protected final Logger logger = LogManager.getLogger(this.getClass());

	protected WebDriverWait wait;
	protected AppiumDriver driver;

	private static final int DEFAULT_TIMEOUT = Integer.parseInt(PropertyReader.getInstance().getProperty("explicit.wait"));

	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		logger.info("Initialized page: " + this.getClass().getSimpleName());
	}

	public void waitForElementToBeVisible(WebElement element, String message) {
		logger.info("Waiting for element to be visible " + message);
		ExtentCucumberAdapter.addTestStepLog("Waiting for element to be visible for " + message);
		wait.until(ExpectedConditions.visibilityOf(element));
		logger.debug("Waiting for element to be visible for " + message + "is successful");
		ExtentCucumberAdapter.addTestStepLog("Waiting for element to be visible for " + message + "is successful");
	}

	public void click(WebElement element, String message) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
		logger.debug("Waiting for element to be clickable for " + message + "is successful");
		ExtentCucumberAdapter.addTestStepLog("Waiting for element to be clickable for " + message + "is successful");
		element.click();
		logger.debug("Clicked on element " + message);
		ExtentCucumberAdapter.addTestStepLog("Clicked on element " + message);
	}

	public void sendKeys(WebElement element, String text, String message) {

		waitForElementToBeVisible(element, message);
		try {
			element.clear();
			element.sendKeys(text);
			logger.debug(message + ":" + text);
			ExtentCucumberAdapter.addTestStepLog(message + ":" + text);
		} catch (InvalidElementStateException e) {
			element.click();
			element.clear();
			element.sendKeys(text);
			logger.debug(message + ":" + text);
			ExtentCucumberAdapter.addTestStepLog(message + ":" + text);
		}
	}

	protected String getText(WebElement element, String message) {
		logger.info("Getting the text value from element");
		waitForElementToBeVisible(element, message);
		return element.getText();
	}

	protected boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			logger.info("Element is not displayed: " + e.getMessage());
			ExtentCucumberAdapter.addTestStepLog("Element is not displayed: " + e.getMessage());
			return false;
		}
	}

	protected boolean isElementEnabled(WebElement element, String message) {
		try {
			waitForElementToBeVisible(element, message);
			return element.isEnabled();
		} catch (Exception e) {
			logger.info("Element is not enabled: " + e.getMessage());
			ExtentCucumberAdapter.addTestStepLog("Element is not enabled: " + e.getMessage());
			return false;
		}
	}
}