package com.testautomation.Utility;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

public class BrowserUtility {

	public WebDriver OpenBrowser(WebDriver driver, String browserName, String url) throws InterruptedException {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//chromeDriver//chromedriver.exe");
			driver = new ChromeDriver(chromeOptions());
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			Thread.sleep(2000);
			return driver;

		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "//ieDriver//IEDriverServer_x32.exe");
			driver = new InternetExplorerDriver(ieCapabilities());
			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "//geckoDriver//geckodriver.exe");
			driver = new FirefoxDriver(firefoxOptions());
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
		}
		return null;
	}

	private static ChromeOptions chromeOptions() {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--disable-popup-blocking");
		chromeOptions.addArguments("--ignore-certificate-errors");
		chromeOptions.addArguments("--disable-translate");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--disable-notifications");

		return chromeOptions;
	}

	private static FirefoxOptions firefoxOptions() {

		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("--test-type");
		firefoxOptions.addArguments("--disable-popup-blocking");
		firefoxOptions.addArguments("--ignore-certificate-errors");
		firefoxOptions.addArguments("--disable-translate");
		firefoxOptions.addArguments("--start-maximized");
		firefoxOptions.addArguments("--disable-notifications");

		return firefoxOptions;
	}

	private static InternetExplorerOptions ieCapabilities() {

		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability("nativeEvents", false);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "accept");
		// options.setCapability("ignoreProtectedModeSettings", true);
		options.setCapability("disable-popup-blocking", true);
		options.setCapability("enablePersistentHover", true);
		options.setCapability("ignoreZoomSetting", true);

		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability("requireWindowFocus", true);// to move mouse manually

		return options;
	}

}