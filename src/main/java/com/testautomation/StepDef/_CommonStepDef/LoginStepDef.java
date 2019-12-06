package com.testautomation.StepDef._CommonStepDef;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.PageObjects._CommonPages.LoginPage;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.java.en.Given;

public class LoginStepDef extends ExtentReportListener
{
	PropertiesFileReader obj;
	Properties properties;
	ExtentTest logInfo = null;

	public LoginStepDef() throws IOException {
		obj = new PropertiesFileReader();
		properties = obj.getProperty();
	}
	
	
	 public String mappedUsers(String userRole) {  // daha sonra DB'den getirilmeli..
	        switch (userRole.toLowerCase()) {
	            case "hisse_ve_viop"                    : return "tmtest test12345";
	            default                                 : return "tmtest test12345";
	        }
	    }

	 	@Given("^I login with \"(.*)\"$")
		public void I_login_with(String user_role) throws Throwable {
			try {
				test = extent.createTest(Feature.class, "Dip Test Cases");	
				logInfo = test.createNode(new GherkinKeyword("Given"), "Go to site: " 
				+ properties.getProperty("browser.baseURL"));

				driver = new BrowserUtility().OpenBrowser(properties.getProperty("browser.name"), properties.getProperty("browser.baseURL"));
				String username = mappedUsers(user_role).split(" ")[0];
				String password = mappedUsers(user_role).split(" ")[1];
				
				new LoginPage(driver)
				.LoginToSystem(username, password)
				.validateHomePage();
		
				logInfo.pass("Browser opened and login to system");
				// logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  // ekran görüntüsü alır

			} catch (AssertionError | Exception e) {
				testStepHandle("FAIL", driver, logInfo, e);
				throw new Exception();
			}
		}	 
	 
	 
//	    private String userDomain(String userRole) {
//	        if (userRole.toLowerCase().contains("bank")) {
//	            return "IS BANKASI";
//	        } else {
//	            return "KGF";
//	        }
//	    }

}
