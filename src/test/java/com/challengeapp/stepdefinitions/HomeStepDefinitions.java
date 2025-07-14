package com.challengeapp.stepdefinitions;

import com.challengeapp.pages.HomePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.challengeapp.capabilities.DriverManager.getDriver;

public class HomeStepDefinitions {

	private static final Logger logger = LogManager.getLogger(HomeStepDefinitions.class);
	private HomePage homePage = new HomePage(getDriver());

	@Then("verify Hotel Tab is displayed")
	public void verify_Hotel_Tab_is_displayed() {
		homePage.verifyHomePageHotelTab();
		logger.info("Hotel Tab is displayed");
	}

	@Then("verify Holidays Tab is displayed")
	public void verify_Holidays_Tab_is_displayed() {
		homePage.verifyHomePageHolidaysTab();
		logger.info("Holidays Tab is displayed");
	}

	@When("click on Hotel Tab")
	public void click_on_Hotel_Tab() {
		homePage.clickHotelTab();
	}

	@Then("click on Holidays Tab")
	public void click_on_Holidays_Tab() {
		homePage.clickHolidaysTab();
	}

	@Then("verify Hotel is displayed")
	public void verify_Hotel_is_displayed() {
		homePage.verifyHotelIsDisplayed();
	}

}