package com.testautomation.StepDef.tkb;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.PageObjects.tkb.KgfLoginPage;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;

public class KgfKurStepDef extends ExtentReportListener {
	PropertiesFileReader obj;
	Properties properties;
	private WebDriver driver;
	ExtentTest logInfo = null;

	public KgfKurStepDef() throws IOException {
		obj = new PropertiesFileReader();
		properties = obj.getProperty();
	}

//	@Given("^Open browser and go to url$")
//	public void Tarayici_ac_ve_kgf_sitesine_git() throws Throwable {
//		try {
//			test = extent.createTest(Feature.class, "KGF Kur Yukleme");
//			test = test.createNode(Scenario.class, "Kgf manuel kur yukleme");
//			logInfo = test.createNode(new GherkinKeyword("Given"), " Tarayici_ac_ve_kgf_sitesine_git");
//
//			driver = BrowserUtility.OpenBrowser(driver, properties.getProperty("browser.name"),
//					properties.getProperty("browser.baseURL"));
//
//			logInfo.pass("Taraci acildi ve adrese ulasildi");
//			// logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
//
//		} catch (AssertionError | Exception e) {
//			testStepHandle("FAIL", driver, logInfo, e);
//			throw new Exception();
//		}
//	}
//
//	@When("^Login with username and password$")
//	public void Kullanici_adi_ve_sifre_girisi() throws Throwable {
//		
//		String kullanici_adi = properties.getProperty("giris.kullanici_adi");
//		String parola = properties.getProperty("giris.parola");
//
//		try {
//			logInfo = test.createNode(new GherkinKeyword("When"), "Kullanici_adi_ve_sifre_girisi");
//			new KgfLoginPage(driver).KgfLogin(kullanici_adi, parola);
//			logInfo.pass("Searching selenium tutorial");
//
//		} catch (AssertionError | Exception e) {
//			testStepHandle("FAIL", driver, logInfo, e);
//		}
//	}
//
//	@Then("^Giris sayfasini kontrol et$")
//	public void Giris_sayfasini_kontrol_et() throws Throwable {
//		try {
//			logInfo = test.createNode(new GherkinKeyword("Then"), "Giris_sayfasini_kontrol_et");
//			new KgfLoginPage(driver)
//			.validateHomePage();
//			
//			logInfo.pass("Anasayfa giris basarili");
//			System.out.println("Tarayici kapatiliyor.");
//			driver.quit();
//
//		} catch (AssertionError | Exception e) {
//			testStepHandle("FAIL", driver, logInfo, e);
//			throw new Exception();
//		}
//
//	}

}
