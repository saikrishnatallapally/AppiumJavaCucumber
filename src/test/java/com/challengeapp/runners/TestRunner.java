package com.challengeapp.runners;

import java.io.IOException;

import org.testng.annotations.BeforeClass;

import com.challengeapp.utils.ExcelUtility;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature", glue = { "com.challengeapp.stepdefinitions",
		"com.challengeapp.hooks" }, plugin = { "pretty", "html:target/cucumber-report.html",
				"json:target/cucumber-report/cucumber.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
		tags = "@hotel", monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {
	
	 @BeforeClass(alwaysRun = true)
	    public void setupProperties() {   
	        if (System.getProperty("platform") == null) {
	            System.setProperty("platform", "android"); // Default device
	        }
	        
	        try {
	            ExcelUtility.excelSheetReading("TestData","Sheet1");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
}
