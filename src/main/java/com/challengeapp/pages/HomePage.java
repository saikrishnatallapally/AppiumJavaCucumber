package com.challengeapp.pages;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage extends BasePage {

	public HomePage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='top_app_bar_hotels_tab']")
	private WebElement hotelsTab;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Holidays\"]")
	private WebElement holidaysTab;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id=\"content_card_root_0\"]")
	private WebElement hotel;

	public void verifyHomePageHotelTab() {
		Assert.assertTrue(isElementDisplayed(hotelsTab), "Home Page Hotel Tab is not displayed");
	}

	public void verifyHomePageHolidaysTab() {
		Assert.assertTrue(isElementDisplayed(holidaysTab), "Home Page holidays Tab is not displayed");
	}

	public void clickHotelTab() {
		click(hotelsTab, "Hotel Tab");
	}

	public void clickHolidaysTab() {
		click(holidaysTab, "Holidays Tab");
	}

	public void verifyHotelIsDisplayed() {

		Assert.assertTrue(isElementDisplayed(hotel), "hotel is not displayed");
	}

}
