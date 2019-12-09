package com.testautomation.PageObjects.dip;


import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Keys;
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
	@FindBy(how = How.CSS, using = "div[aria-label='Al-Sat']") 													WebElement btn_AlSat;
	@FindBy(how = How.CSS, using = "div.purchase-sell-window")				 									WebElement popup_AlSat;
	@FindBy(how = How.CSS, using = "#txtPxEquity > div:nth-child(2) > div > div > input") 						WebElement txt_Fiyat;
	@FindBy(how = How.CSS, using = "input[aria-haspopup='listbox']")				 							WebElement txt_Sembol;
	@FindBy(how = How.CSS, using = "#txtPxEquity > div:nth-child(1)")				 							WebElement btn_FiyatMinus;
	@FindBy(how = How.CSS, using = "#txtPxEquity > div:nth-child(3)")				 							WebElement btn_FiyatPlus;
	@FindBy(how = How.CSS, using = "div[aria-label='close']")				 									WebElement btn_Close;
	

	//*********Methods*********
	public HomePage returnToHomePage()
	{
		navigateToURL(properties.getProperty("browser.homePage"));
		return new HomePage(driver);
	}
	
	
	public EmirlerPage clickToAlSatButton()
	{
		click(btn_AlSat);
		return this;
	}
	
	
	public EmirlerPage validateAlSatPopup()
	{
		assertTrue(popup_AlSat);
		return this;
	}
	
	public EmirlerPage setFiyatAndSembol(String fiyat, String sembol)
	{		
		writeText(txt_Sembol, sembol);
		wait(1);
		txt_Sembol.sendKeys(Keys.ENTER);
		wait(1);
		writeText(txt_Fiyat, fiyat);

		return this;
	}

	public EmirlerPage inputEventToPopup(String input)
	{
		switch(input) {
		  case "increase":
			  click(btn_FiyatPlus); // artı butonuna tıklayıp arttırma
		    break;
		  case "decrease":
			  click(btn_FiyatMinus); // eksi butonuna tıklayıp sifirin altina gitmedigini gor
		    break;
		  case "keyboard_up":
			  writeKeyboardButton(txt_Fiyat, Keys.ARROW_UP); // klavye yukari ok
		    break;
		  case "keyboard_down":
			  writeKeyboardButton(txt_Fiyat, Keys.ARROW_DOWN); // klavye asagi ok
		    break;
		  case "scroll_up":
			  writeKeyboardButton(txt_Fiyat, Keys.PAGE_UP); // scroll bar + 1
		    break;
		    
		    
		  default:
		    // code block
		}
		
		return this;
	}
	
	public EmirlerPage validateFiyatValue(String input)
	{
		String newFiyat = getAttiribute(txt_Fiyat, "value");
		assertEquals(newFiyat, input);
		return this;
	}
	
	public EmirlerPage closePopup()
	{
		setAttribute(popup_AlSat, "style", "top:50px");
		click(btn_Close);
		return this;
	}
	
	
}
