package com.challengeapp.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username_input_field']")
	private WebElement usernameField;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password_input_field']")
	private WebElement passwordField;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='date_of_birth_field_calendar_icon']/android.widget.Button")
	private WebElement dobCalendarIcon;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='date_of_birth_dialog']/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.Button")
	private WebElement selectedDateInPicker;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement dateInputField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Confirm\"]")
	private WebElement dateConfirmButton;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='login_form_submit_button']/android.widget.Button")
	private WebElement loginButton;

	public LoginPage(AppiumDriver driver) {
		super(driver);
		logger.info("Login Page has initialized");
	}

	public LoginPage enterUsername(String username) {
		sendKeys(usernameField, username, "Enter username value" + username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		sendKeys(passwordField, password, "Enter password value" + password);
		return this;
	}

	public LoginPage setDateOfBirth(String date) {
		click(dobCalendarIcon, "Setting date of birth value " + date);
		click(selectedDateInPicker, "Date Picker");
		dateInputField.click();
		sendKeys(dateInputField, date, "Enter date value " + date);
		click(dateConfirmButton, "date Confirm button");
		return this;
	}

	public void clickLogin() {
		click(loginButton, "login button");
	}
}