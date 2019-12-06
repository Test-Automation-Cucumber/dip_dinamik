package com.testautomation.Utility;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PageBase {

	public WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public PageBase(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 120);
		this.js = (JavascriptExecutor) driver;
	}

	private void ajaxComplete() {
		js.executeScript("var callback = arguments[arguments.length - 1];" + "var xhr = new XMLHttpRequest();"
				+ "xhr.open('GET', '/Ajax_call', true);" + "xhr.onreadystatechange = function() {"
				+ "  if (xhr.readyState == 4) {" + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
	}

	private void waitForJQueryLoad() {
		try {
			ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) this.driver)
					.executeScript("return jQuery.active") == 0);

			boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

			if (!jqueryReady) {
				wait.until(jQueryLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	private void waitForAngularLoad() {
		String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
		angularLoads(angularReadyScript);
	}

	private void waitUntilJSReady() {
		try {
			ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) this.driver)
					.executeScript("return document.readyState").toString().equals("complete");

			boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

			if (!jsReady) {
				wait.until(jsLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	private void waitUntilJQueryReady() {
		Boolean jQueryDefined = (Boolean) js.executeScript("return typeof jQuery != 'undefined'");
		if (jQueryDefined) {
			poll(20);

			waitForJQueryLoad();

			poll(20);
		}
	}

	private void waitUntilAngularReady() {
		try {
			Boolean angularUnDefined = (Boolean) js.executeScript("return window.angular === undefined");
			if (!angularUnDefined) {
				Boolean angularInjectorUnDefined = (Boolean) js
						.executeScript("return angular.element(document).injector() === undefined");
				if (!angularInjectorUnDefined) {
					poll(20);

					waitForAngularLoad();

					poll(20);
				}
			}
		} catch (WebDriverException ignored) {
		}
	}

	private void waitUntilAngular5Ready() {
		try {
			Object angular5Check = js.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
			if (angular5Check != null) {
				Boolean angularPageLoaded = (Boolean) js
						.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
				if (!angularPageLoaded) {
					poll(20);

					waitForAngular5Load();

					poll(20);
				}
			}
		} catch (WebDriverException ignored) {
		}
	}

	private void waitForAngular5Load() {
		String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
		angularLoads(angularReadyScript);
	}

	private void angularLoads(String angularReadyScript) {
		try {
			ExpectedCondition<Boolean> angularLoad = driver -> Boolean
					.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());

			boolean angularReady = Boolean.valueOf(js.executeScript(angularReadyScript).toString());

			if (!angularReady) {
				wait.until(angularLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	public void waitAllRequest() {
		waitUntilJSReady();
		ajaxComplete();
		waitUntilJQueryReady();
		waitUntilAngularReady();
		waitUntilAngular5Ready();
	}

	/**
	 * Method to make sure a specific element has loaded on the page
	 *
	 * @param by
	 * @param expected
	 */
	public void waitForElementAreComplete(List<WebElement> elements, int expected) {
		waitAllRequest();
		ExpectedCondition<Boolean> angularLoad = driver -> {
			int loadingElements = elements.size();
			return loadingElements >= expected;
		};
		wait.until(angularLoad);
	}

	/**
	 * Waits for the elements animation to be completed
	 * 
	 * @param css
	 */
	public void waitForAnimationToComplete(String css) {
		ExpectedCondition<Boolean> angularLoad = driver -> {
			int loadingElements = this.driver.findElements(By.cssSelector(css)).size();
			return loadingElements == 0;
		};
		wait.until(angularLoad);
	}

	private void poll(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Wait Wrapper Method
	protected void waitVisibility(WebElement element) {
		try {
			waitAllRequest();
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOf(element));
	}// Wait Wrapper Method


	protected void waitClickable(WebElement element) {
		try {
			waitAllRequest();
			highlightElement(element);
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForAjax() {
		new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				JavascriptExecutor js = (JavascriptExecutor) d;
				return (Boolean) js.executeScript("return jQuery.active == 0");
			}
		});
	}

	private void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: red; border: 1px dashed red;");
	}

	protected void navigateToURL(String URL) {
		driver.navigate().to(URL);
	}

	// Click Method
	protected void click(WebElement element) {
		waitClickable(element);
		element.click();
	}

//	protected void selectOption(By elementBy) {
//		waitAllRequest();
//		List<WebElement> list = driver.findElements(elementBy);
//		//WebElement element = list.get(0);
//		waitClickable(elementBy);
//		highlightElement(driver.findElement(elementBy));
//		Select select = new Select(driver.findElement(elementBy));
//		select.by
//	}

	protected void sendHotKey(String text) {
		Actions keyAction = new Actions(driver);
		keyAction.sendKeys(text).perform();
	}
	
	// Write Text
	protected void writeText(WebElement element, String text) {
		waitClickable(element);
		element.clear();
		element.sendKeys(text);
	}

	// Write Text Element
	/**
	 * @param elementBy
	 * @param index     -- aynı elementten başka varsa index değerini gönder.
	 * @param index     -- aynı elementten başka varsa index değerini gönder.
	 */
	protected void writeText(By elementBy, int index, String text) {
		List<WebElement> list = driver.findElements(elementBy);
		WebElement element = list.get(index);
		waitClickable(element);
		element.clear();
		element.sendKeys(text);
	}

	
	
	// Read Text
	protected String readText(WebElement element) {
		waitClickable(element);
		return element.getText();
	}
	
	
	// Read Text
	/**
	 * @param element
	 * @param index     -- aynı elementten başka varsa index değerini gönder.
	 */
	protected String readText(WebElement element, int index) {
		waitClickable(element);
		return element.getText();
	}

	// Mouse Over
	protected void mouseOver(WebElement element) {
		waitClickable(element);
		Actions myAction = new Actions(driver);
		myAction.moveToElement(element).build().perform();
	}

	// Assert - element
	protected void assertEquals(WebElement element, String expected_text) {
		waitClickable(element);
		Assert.assertEquals(readText(element), expected_text);
	}
	
	// AssertTrue  (or-False)
	protected void assertTrue(WebElement element) {
		waitClickable(element);
		Assert.assertTrue(element.isDisplayed());
	}


		
		// Exists Element
		/**
		 * @param element
		 * @param second    -- set seconds to wait element in page
		 * @return returns true or false regarding to element existing
		 */
		protected boolean elementExists(WebElement element, int second) {
			try {
				for (int i = 0; i < second; i++) {
					Thread.sleep(1000);
					if (element.isDisplayed()) {
						return true;
					}
				}
				return false;

			} catch (Exception e) {
				return false;
			}
		}

		//bekle
	protected void wait(int second) {
		try {
			Thread.sleep(Integer.parseInt(second + "000"));
		} catch (NumberFormatException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void goToEndOfPage() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	protected void goToTopOfPage() {
		js.executeScript("window.scrollTo(0, 0)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected String executeJS(String txt) {
		wait(1);
		return (String) js.executeScript(txt);
	}


	// getRowCountOfList
	protected int getCountOfList(WebElement element, String children_tag_name) {
		List<WebElement> tableRows = element.findElements(By.tagName(children_tag_name));
		return tableRows.size();
	}

	// Go to Page
	protected void goToWebPage(String url) {
		driver.get(url);
	}

	
	// checked Method
	protected void checked(By elementBy, int index) {
		List<WebElement> list = driver.findElements(elementBy);
		WebElement element = list.get(index);
		if (!element.isSelected()) {
			waitClickable(element);
			element.click();
		}
	}

	// UNchecked Method
	protected void unchecked(List<WebElement> elements, int index) {
			WebElement element = elements.get(index);
		if (element.isSelected()) {
			waitClickable(element);
			element.click();
		}
	}

	// UNchecked Method
	protected void unchecked(WebElement element) {
		waitClickable(element);
		if (element.isSelected()) {
			element.click();
		}
	}

	// Wait Loading Image
//	protected void waitLoadingImg(By elementBy) {
//		try {
//			waitForPageToLoad();
//			Thread.sleep(1500);
//
//			for (int i = 0; i < 60; i++) {
//				// if
//				// (!driver.findElement(loadingImageBy).getCssValue("visibility").equals("hidden"))
//				if (!driver.findElement(elementBy).getAttribute("display").equals("none")) {
//					Thread.sleep(1500);
//				} else {
//					break;
//				}
//			}
//		}catch (Exception ex) {
//			}
//			
//		}

	// Wait Loading Image
	protected void waitLoadingTableImg() {
		try {
			Thread.sleep(1500);

			for (int i = 0; i < 60; i++) {
				// if
				// if(!driver.findElement(elementBy).getCssValue("opacity").equals("1"))
				if (driver.findElement(By.cssSelector("div.dx-loadpanel-content-wrapper")).isDisplayed()) {
					Thread.sleep(1000);
				} else {
					break;
				}
			}
		} catch (Exception ex) {
		}
	}

	protected void waitVolatileElement(By elementBy, int second) {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
//		try {
//			List<WebElement> elements;
//			for (int i = 0; i < second; i++) {
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				elements = driver.findElements(elementBy);
//				if (elements.size() < 1)
//					return;
//				else if (i == second - 1) {
//					try {
//						throw new Exception("kaybolması beklenen element ekranda görünmeye devam ediyor.");
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					return;
//				}
//			}
//		} catch (Exception ex) {
//
//		}
	}

	// Select html <li> Item
	protected List<WebElement> selectiItem(WebElement ItemsUL, String tagname) {
		List<WebElement> ItemsList = ItemsUL.findElements(By.tagName(tagname));
		return ItemsList;
	}

	protected String SetRandomString(int byteLength) {

		String text = "";
		for (int i = 0; i < byteLength; i++) {
			Random rnd = new Random();
			char c = (char) (rnd.nextInt(26) + 'a');
			text = text + c;
		}
		return text;
	}

	
	// Get Child Element
	/**
	 * @param elementBy -- main element
	 * @param tagName   -- main elementin 1. seviye altında aranacak child tag adı
	 *                  örnek : li tagı.
	 * @param index     -- gidilecek olan child tagın sırasını giriniz. örnek :
	 *                  (elementBy= XYZ, tagName=li, index=5) = XYZ elementinin
	 *                  altındaki 5. li tagının By değerini döndürür.
	 */
	protected WebElement getChild(WebElement element, String tagName, int index) {
		List<WebElement> tableRows = element.findElements(By.tagName(tagName));
		return tableRows.get(index);
	}


	
	
	// Get Attiribute 
	protected String getAttiribute(By elementBy) {
		waitAllRequest();
		return driver.findElement(elementBy).getAttribute("value");
		}
	
	
	// Switch Tab Page
	protected void switchToNewTab(boolean kill_old_tab) {
		waitAllRequest();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		waitAllRequest();
		if (kill_old_tab) {
			driver.switchTo().window(tabs.get(0));
			driver.close();
			driver.switchTo().window(tabs.get(1));
		}


		}
	
	
	public void fixToString()
	{
		try {
	        Robot robot = new Robot();
	        
	        // Simulate a key press
	        robot.keyPress(KeyEvent.VK_A);
	        robot.keyRelease(KeyEvent.VK_BACK_SPACE);

	} catch (AWTException e) {
	        e.printStackTrace();
	}
	}
	
	
	//////////
	protected void clickElementJs(String element_attribute, int index) {
		waitAllRequest();
		wait(1);
		js.executeScript("return document.querySelectorAll('" + element_attribute + "')[" + index + "].click();");
	}
	
	protected void writeElementJs(String element_attribute, int index, String value) {
		waitAllRequest();
		wait(1);
		js.executeScript("return document.querySelectorAll('" + element_attribute + "')[" + index + "].value ='" + value + "';");

	}
	
	protected void readElementJs(String type, String element_attribute , int index) {
		waitAllRequest();
		wait(1);
		js.executeScript("return document.querySelectorAll('" + element_attribute + "')[" + index + "].value;");
		
	}
	//////////////
	
	
	
	/// bi ara  bunuda düzelt.
	protected void removeElementJs(String type, String value) {
		wait(1);
		if (type == "id")
			js.executeScript("return document.getElementById('" + value + "').remove();");
		else if (type == "class")
			js.executeScript("return document.getElementsByClassName('" + value + "')[0].remove();");
	}
	
	// logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  // ekran görüntüsü alır
	
}
