package com.testautomation.StepDef._CommonStepDef;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.ReportConfigurator;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.PageObjects._CommonPages.LoginPage;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.java.en.Given;

public class HomePageStepDef extends ExtentReportListener
{
	PropertiesFileReader obj;
	Properties properties;
	private WebDriver driver;
	ExtentTest logInfo = null;

	public HomePageStepDef() throws IOException {
		obj = new PropertiesFileReader();
		properties = obj.getProperty();
	
	}
	
}