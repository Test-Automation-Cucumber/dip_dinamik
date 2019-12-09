package com.testautomation.StepDef.dip;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.PageObjects._CommonPages.LoginPage;
import com.testautomation.PageObjects.dip.EmirlerPage;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.Login;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;

public class EmirlerStepDef extends ExtentReportListener {
	PropertiesFileReader obj;
	Properties properties;
	private WebDriver driver;
	ExtentTest logInfo = null;

	public EmirlerStepDef() throws Throwable {
		obj = new PropertiesFileReader();
		properties = obj.getProperty();
	}

	@Given("^Emirler - I login with \"(.*)\"$")
	public void I_login_with(String user_role) throws Throwable {
		try {
			test = extent.createTest(Feature.class, "Dip Test Cases");
			logInfo = test.createNode(new GherkinKeyword("Given"),
					"Go to site: " + properties.getProperty("browser.baseURL"));

			driver = new BrowserUtility().OpenBrowser(driver, properties.getProperty("browser.name"), properties.getProperty("browser.baseURL"));
		
			
			String[] loginInfo = new Login().mappedUsers(user_role); //kullanıcı adi, parola bilgisi çekilir
			
			new LoginPage(driver)
			.LoginToSystem(loginInfo[0], loginInfo[1]).validateHomePage();

			logInfo.pass("Browser opened and login to system");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}
	}
	
	@When("I click Al-Sat button$")
	public void I_click_Al_Sat_button() throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), "Al-Sat butonuna tiklanir");

			new EmirlerPage(driver)
			.clickToAlSatButton();

			logInfo.pass("Al-Sat buton tiklama basarili");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}
	}

	@Then("^I should see Yeni Emir popup$")
	public void I_should_see_Yeni_Emir_popup() throws Throwable {
		try {

			new EmirlerPage(driver)
			.validateAlSatPopup();
			
			logInfo = test.createNode(new GherkinKeyword("Then"), "Popup penceresini görüntüleme basarili");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}

	}

	
	@When("^Set Fiyat value \"(.*)\" and Sembol \"(.*)\"$")
	public void Set_Fiyat_and_Symbol(String fiyat, String sembol) throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), "Sembol:" + sembol + "Fiyat:" + sembol);

			new EmirlerPage(driver)
			.setFiyatAndSembol(fiyat, sembol);

			logInfo.pass("Sembol ve Fiyat girisi yapildi");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}
	}
	
	@When("^Press button \"(.*)\"$")
	public void I_click_button(String input) throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), input + "tuşuna basilir");

			new EmirlerPage(driver)
			.inputEventToPopup(input);

			logInfo.pass(input +" girisi yapildi");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}
	}
	
	@Then("^Fiyat field must be \"(.*)\"$")
	public void validate_Fiyat_field(String new_value) throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "beklenen yeni deger: "+ new_value);

			new EmirlerPage(driver)
			.validateFiyatValue(new_value)
			.closePopup();

			logInfo.pass("gorunen deger '"+ new_value +"' oldu");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
			throw new Exception();
		}
	}
	
}
