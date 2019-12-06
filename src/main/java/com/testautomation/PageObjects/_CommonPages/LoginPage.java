package com.testautomation.PageObjects._CommonPages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.testautomation.StepDef._CommonStepDef.LoginStepDef;
import com.testautomation.Utility.PageBase;

public class LoginPage extends PageBase {

	// *********Constructor*********
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// *********Web Elements*********
	@FindBy(how = How.NAME, using = "txtUsername") 									WebElement txt_username;
	@FindBy(how = How.NAME, using = "txtPassword")									WebElement txt_password;
	@FindBy(how = How.CLASS_NAME, using = "dx-button-text") 						WebElement btn_login;
	@FindBy(how = How.CSS, using = "input[class='form-control']") 					WebElement txt_otp;
	@FindBy(how = How.CSS, using = "div[aria-label='Giriş']") 						WebElement btn_otpLogin;
	@FindBys(@FindBy(how = How.CSS, using = "img[alt='logo']")) 			  		List<WebElement> img_appLogo;
	
	//**********Methods**************
	public LoginPage LoginToSystem(String p_username, String p_password) throws Throwable {
		writeText(txt_username, p_username);
		writeText(txt_password, p_password);
		click(btn_login);
		writeText(txt_otp, "494077");
		click(btn_otpLogin);
		return this;
	}

	public LoginPage validateHomePage() {
		assertTrue(img_appLogo.get(0));
		return this;
	}

	
}
