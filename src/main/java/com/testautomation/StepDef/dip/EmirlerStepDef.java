package com.testautomation.StepDef.dip;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.PageObjects.dip.EmirlerPage;

import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

//import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;

public class EmirlerStepDef extends ExtentReportListener {
	PropertiesFileReader obj;
	Properties properties;
	ExtentTest logInfo = null;
	BrowserUtility browserUtility;

	public EmirlerStepDef() throws Throwable {
		obj = new PropertiesFileReader();
		properties = obj.getProperty();
	}

	@When("I click Al-Sat button$")
	public void I_click_Al_Sat_button() throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), "Al-Sat butonuna tiklanir");

			new EmirlerPage(driver).clickToAlSatButton().validateAlSatPopup();

			logInfo.pass("Al-Sat buton tiklama basarili");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}
	}

	@Then("^I should see Yeni Emir popup$")
	public void I_should_see_Yeni_Emir_popup() throws Throwable {
		try {

			new EmirlerPage(driver).validateAlSatPopup().returnToHomePage();
			logInfo = test.createNode(new GherkinKeyword("Then"), "Popup penceresini görüntüleme basarili");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}

	}

}
