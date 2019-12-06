package com.testautomation.PageObjects.tkb;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.Utility.PageBase;

import junit.framework.Assert;


public class KgfLoginPage extends PageBase 
{

    //*********Constructor*********
	public KgfLoginPage(WebDriver driver)
	{
        super(driver);
		PageFactory.initElements(driver, this);
	}
	
    //*********Web Elements*********
	@FindBy(how=How.ID,using="login_username") WebElement KullaniciAdi;
	@FindBy(how=How.ID,using="login_password") WebElement Parola;
	@FindBy(how=How.ID,using="login_id") WebElement GirisButon;
	@FindBys(@FindBy(how=How.CLASS_NAME,using="application_logo")) List<WebElement> kgfLogo;
	
	
	
	public KgfLoginPage KgfLogin(String kadi,String parola)
	{
		KullaniciAdi.sendKeys(kadi);
		Parola.sendKeys(parola);
		GirisButon.click();
		return this;
	}
	
	
	public KgfLoginPage validateHomePage()
	{
		waitAllRequest();
		Assert.assertTrue(kgfLogo.get(0).isDisplayed());
		return this;
	}
	

}
