package com.testautomation.PageObjects.dip;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.Utility.PageBase;

import junit.framework.Assert;


public class _templatePage extends PageBase 
{

    //*********Constructor*********
	public _templatePage(WebDriver driver)
	{
        super(driver);
		PageFactory.initElements(driver, this);
	}
	
    //*********Web Elements*********
	@FindBy(how = How.ID, using = "login_id") 										WebElement loginButton;
	@FindBys(@FindBy(how = How.CLASS_NAME, using = "application_logo")) 	  		List<WebElement> appLogo;
	
	
	//*********Methods************
	public _templatePage KgfLogin(String kadi,String parola)
	{
		click(loginButton);
		return this;
	}
	
	
	public _templatePage validateHomePage()
	{
		waitAllRequest();
		Assert.assertTrue(appLogo.get(0).isDisplayed());
		return this;
	}
	

}
