package com.testautomation.Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.internal.Nullable;

public class BrowserUtility {
	
public static WebDriver driver;

	public WebDriver OpenBrowser(String browserName, String url) throws InterruptedException {
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

	////////////////
	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;

	public static ExtentReports setUp() {
		String reportLocation = "./Reports/Extent_Report.html";
		report = new ExtentHtmlReporter(reportLocation);
		report.config().setDocumentTitle("Automation Test Report");
		report.config().setReportName("Automation Test Report");
		report.config().setTheme(Theme.STANDARD);
		System.out.println("Extent Report location initialized . . .");
		report.start();

		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "Youtube");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System Info. set in Extent Report");
		return extent;
	}

	public static void testStepHandle(String teststatus, WebDriver driver, ExtentTest extenttest, Throwable throwable) {
		switch (teststatus) {
		case "FAIL":
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
			extenttest.error(throwable.fillInStackTrace());

			try {
				extenttest.addScreenCaptureFromPath(captureScreenShot(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (driver != null) {
				 driver.quit();
			}
			break;

		case "PASS":
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;

		default:
			break;
		}
	}

	public static String captureScreenShot(WebDriver driver) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + getcurrentdateandtime() + ".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

	private static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}

}
