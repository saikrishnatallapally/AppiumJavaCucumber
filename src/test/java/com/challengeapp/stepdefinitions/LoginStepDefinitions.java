package com.challengeapp.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.challengeapp.pages.HomePage;
import com.challengeapp.pages.LoginPage;
import com.challengeapp.utils.ExcelUtility;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.challengeapp.capabilities.DriverManager.getDriver;

public class LoginStepDefinitions {

	private static final Logger logger = LogManager.getLogger(LoginStepDefinitions.class);
	private LoginPage loginPage;

	@Given("I am on the login page")
	public void I_am_on_the_login_page() {
		logger.info("Navigating to the application login page");
		ExtentCucumberAdapter.addTestStepLog("Navigating to the application login page");
		loginPage = new LoginPage(getDriver());
		logger.info("Successfully navigated to login page");
	}

	@When("I enter valid username")
	public void i_Enter_Valid_Username() {
		loginPage.enterUsername(ExcelUtility.testData.get("username"));
	}

	@And("I enter valid password")
	public void i_Enter_Valid_Password() {
		loginPage.enterPassword(ExcelUtility.testData.get("password"));
	}

	@And("I enter date of birth {string}")
	public void i_Enter_Date_Of_Birth(String dateOfBirth) {
		loginPage.setDateOfBirth(dateOfBirth);
	}

	@And("I click on login button")
	public void i_Click_on_Login_Button() {
		loginPage.clickLogin();
	}

	@Then("user land on Home page")
	public void user_land_on_Home_page() {
		logger.info("Verifying successful login");
		HomePage homePage = new HomePage(getDriver());
		homePage.verifyHomePageHotelTab();
		logger.info("Logged in successfully");
	}

}