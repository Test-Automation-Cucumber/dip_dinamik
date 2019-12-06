package com.testautomation.PageObjects._CommonPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.testautomation.Utility.*;

import junit.framework.Assert;


public class HomePage extends PageBase 
{
    //*********Constructor*********
	public HomePage(WebDriver driver)
	{
        super(driver);
		PageFactory.initElements(driver, this);
	}
	
    //*********Constructor*********
	@FindBys(@FindBy(how=How.CLASS_NAME,using="application_logo")) List<WebElement> GiriskButon;
	
	public HomePage validateHomePage()
	{
		Assert.assertTrue(GiriskButon.get(0).isDisplayed());
		return this;
	}
	
	public HomePage test(String kadi,String parola)
	{
		return this;
	}
	
	

	

}
