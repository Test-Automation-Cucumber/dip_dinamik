package com.testautomation.PageObjects.dip;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.PageObjects._CommonPages.HomePage;
import com.testautomation.Utility.PageBase;
import com.testautomation.Utility.PropertiesFileReader;


public class EmirlerPage extends PageBase 
{
	PropertiesFileReader obj;
	Properties properties;
	
	
    //*********Constructor*********
	public EmirlerPage(WebDriver driver) throws Throwable
	{
        super(driver);
		PageFactory.initElements(driver, this);
		obj = new PropertiesFileReader();
		properties = obj.getProperty();
	}
	
    //*********Web Elements*********
	@FindBy(how = How.CSS, using = "div[aria-label='Al-Sat']") 										WebElement alsatButton;
	@FindBy(how = How.CSS, using = "div.purchase-sell-window")				 						WebElement alsatPopup;

	
	
	//*********Methods*********
	public HomePage returnToHomePage()
	{
		navigateToURL(properties.getProperty("browser.homePage"));
		return new HomePage(driver);
	}
	
	
	public EmirlerPage clickToAlSatButton()
	{
		click(alsatButton);
		return this;
	}
	
	
	public EmirlerPage validateAlSatPopup()
	{
		assertTrue(alsatPopup);
		return this;
	}
	


}
