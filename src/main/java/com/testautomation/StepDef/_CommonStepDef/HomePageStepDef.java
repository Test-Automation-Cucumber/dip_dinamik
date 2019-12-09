package com.testautomation.StepDef._CommonStepDef;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.Utility.PropertiesFileReader;


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