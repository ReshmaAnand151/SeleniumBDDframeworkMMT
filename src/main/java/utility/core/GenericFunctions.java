package utility.core;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;

import com.cucumber.listener.Reporter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GenericFunctions {

	public static WebDriver driver;
	public static Set<String> set = null;
	public static String actualColor = null;
	private static String placeholder;
	
	//constructor
	public GenericFunctions (WebDriver driver) {
		this.driver = driver;
	}
	
	public static void deleteCookies() {
		driver.manage().deleteAllCookies();
	}
	public static void waitForpageLoad(final long timeOut) throws Exception{
		try {
			Timeouts timeOuts = driver.manage().timeouts();
			timeOuts.pageLoadTimeout(Duration.ofSeconds(timeOut));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void switchTotheLastWindow() throws IOException{
		Set<String> winHandles=driver.getWindowHandles();
		for(String winHandle : winHandles) {
			driver.switchTo().window(winHandle);
		}
	}
	public static void popwindow() throws InterruptedException, AWTException{
		StringSelection ss = new StringSelection("path");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[id='OK']")).click();
		Thread.sleep(2000);
	}

	public static void windowMaximize() {
		driver.manage().window().maximize();
	}
	public static void sort() throws Exception{
		try {
			ArrayList<String> obtainedList = new ArrayList<>();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void setText(final WebElement element, final String value, final Duration timeOut) throws Exception{
		try {
			if(isElementVisible(element, timeOut)) {
				element.clear();
				element.sendKeys(value);
			if(element.getAttribute("id").contains("password") || element.getAttribute("name").contains("password")) {
				Reporter.addStepLog("Entered the password Successfully");
			}else {
				Reporter.addStepLog("As Expected, the text '" + getText(element, 60) + "' entered successfully");
			}
		}else {
				if(element.getAttribute("id").contains("password") || element.getAttribute("name").contains("password")) {
					Assert.fail("Failed to set the password");
				}else {
					Assert.fail("Failed to set the value = " + value);
				}
			}
		}catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
	}
}
	private static String getText(WebElement element, int i) {
		return null;
	}
	
	public static void simpleWait(int millisec) throws Exception{
		try {
			Thread.sleep(millisec);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*this method will wai until element ound in given amount of time
	 * 
	 * @param millisec
	 * time to wait
	 * @throws Exception
	 * error
	
	*/
	
	public static void waitForElementVisible(WebElement locator) {
		String documentation ="Waiting upto 15000 ms for element with locator:\"" + locator +"\" to appear on page."
				+ "If timed out, will refresh the page and check once again";
		System.out.println(documentation);
		
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1));
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(locator));
		}
		catch(Exception e) {
			System.err.println("Unable to find the element in wait condition or given time" + e.getMessage());
		}
	}
	
	/* This method will check whether the given element is clickable or not */
	
	public static boolean isElementClickable(final WebElement element, final Duration timeOut) throws Exception{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			if(wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
				return true;
			}else {
				Assert.fail("Element is not clickable" + element);
				return false;
			}
		}catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
		}
	}
	/*
	 * 
	 * this method will click on given elelment using selenese command*/
	public static void click(final WebElement element, final Duration timeOut, final String textToDisplay) throws Exception{
		try {
			if(isElementClickable(element, timeOut)) {
				element.click();
				Reporter.addStepLog("As Expected, "+ textToDisplay + "Clicked Successfully");
			}else {
				Assert.fail("Failed to click on the" + textToDisplay);
			}
		}catch(final Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
		}
	}
	/*This method will click on iven element using javascript executor
	 * */
	public static void jsClick(final WebElement element, final Duration timeOut, final String textToDisplay) throws Exception{
		try {
			if(isElementClickable(element, timeOut)) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				simpleWait(1000);
				Reporter.addStepLog("As Expected, "+ textToDisplay + "Clicked Successfully");
			}else {
				Assert.fail("Failed to click on the" + textToDisplay);
			}
		}catch(final Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
		}
	}
	/*This method will click on iven element using javascript executor
	 * */
	public static boolean jsClick(final WebElement element, final Duration timeOut) throws Exception{
		try {
			if(isElementClickable(element, timeOut)) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				return true;
			}else {
				return false;
			}
		}catch(final Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
		}
	}
/*This method will check whether given element is visible or not
	 * */	
	public static boolean isElementVisible(final WebElement element, final Duration timeOut) throws Exception{
		try {
		
			final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					if(element.getSize() != null) {
						return element.isDisplayed();
					}else {
						return false;
					}
				}
			};
			final Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			try{
				wait.until(expectation);
				return true;
			}catch(final Exception e) {
				return false;
			}
		}catch(final Exception ex) {
			Assert.fail(element +" is not Found");
			return false;
		}
	}
	/*
	 * 
	 * */
	public static boolean isElementVisible(final WebElement element, final Duration timeOut, final String textToDisplay) {
		try {
		
			final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					
					if(element.getSize() != null) {
						element.isDisplayed();
						Reporter.addStepLog("As Expected, "+ textToDisplay + "is present");

						return true;
					}else {
						Assert.fail("\"" + textToDisplay + "\"" + "is not found");
						return false;
					}
				}
			};
			final Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			try{
				wait.until(expectation);
				return true;
			}catch(final Exception e) {
				throw e;
			}
		}catch(final Exception ex) {
			Assert.fail(element +" is not Found");
			throw ex;
			}
	}
	
	
	//this method is for validate the length of web elemtn text
	public static boolean isElementTextVisible(final WebElement element, final Duration timeOut) {
		try {
		
			final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				
				public Boolean apply(final WebDriver driver) {
					System.out.println("Element text display under wrapper method: "+ element.getText());
					System.out.println("Element text display under wrapper method: "+ element.getText().length());
					int l = element.getText().length();
					if(l != 0) {
						Reporter.addStepLog("As Expected, "+ element.getText() + "is present");
						return true;
					}else {
						Assert.fail("\"" + element.getText() + "\"" + "is not found");
						return false;
					}
				}
			};
			final Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			try{
				wait.until(expectation);
				return true;
			}catch(final Exception e) {
				throw e;
			}
		}catch(final Exception ex) {
			Assert.fail(element +" is not Found");
			throw ex;
			}
	}
	
	/*this method will get the texxt or attribute value of given element
	 * 
	 * 
	 * */
	
	public static String getText(final WebElement element, final Duration i) throws Exception {
		String textMessage = null;
		try {
		if(isElementVisible(element, i)) {
			textMessage = element.getText().trim();
			if(textMessage.equals("null") || textMessage.equals("")) {
				textMessage = element.getAttribute("value").trim();
			}
			return textMessage;
		}else {
			Assert.fail("Failed to get the value");
			return null;
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/*this method will get the color of given element
	 * 
	 * 
	 * */
	public static String getColor(final WebElement element, final Duration timeOut) throws Exception {
		String color = null;
		try {
		if(isElementVisible(element, timeOut)) {
			color = element.getCssValue("color");
			return color;
			}else {
			Assert.fail("Failed to get the color of element");
			return null;
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/*this method will get the background color of given element
	 * 
	 * 
	 * */
	public static String getbkColor(final WebElement element, final Duration timeOut) throws Exception {
		String color = null;
		try {
		if(isElementVisible(element, timeOut)) {
			color = element.getCssValue("background-color");
			return color;
			}else {
			Assert.fail("Failed to get the color of element");
			return null;
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	
	/*this method will get the title of page
	 * 
	 * 
	 * */
	public static String getPageTitle() throws Exception {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
			return pageTitle;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/*this method will get the url of page and will validate it with expected url value
	 * 
	 * 
	 * */
	public static void validateUrl(final String expectedUrl) throws Exception {
		String currentUrl = driver.getCurrentUrl().trim().toLowerCase();
		try {
			if(currentUrl.contains(expectedUrl.trim().toLowerCase())) {
				Reporter.addStepLog("As Expected, Navigated to the url = "+ currentUrl);
			}else if(currentUrl.contains("auth_cred_submit"))
			{
				Reporter.addStepLog("\"" + "sso ailure occured" +"\"");
			}else {
				Assert.fail("Url mismatch." + "The expected url is " +expectedUrl + " and the actual url is " +currentUrl);
			}
		}
		catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
		}	
	simpleWait(3000);
}
	
	public static void validateText(String expectedText, WebElement element, final Duration timeOut) throws Exception {
		try {
			String actualText = getText(element, timeOut).trim().toLowerCase();
			expectedText = expectedText.trim().toLowerCase();
			if(actualText.contains(expectedText)) {
				Reporter.addStepLog("As Expected, "+ actualText + "text is present");
				}
			else {
				Assert.fail("Text value mismatch." + "the expected value is " + "\"" +expectedText + "\"" +"and the atual value is " + "\"" +actualText + "\"");
			}
		}
		catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
		}	
	}	
	
	
	private static String getAttribute(WebElement element, String attributeName, int i) {
		return null;
	}
	
	/*method will switch the driver control to new popup window
	 * */
	public static void SwitchToChildWindow() throws Exception{
		try {
			set = driver.getWindowHandles();
			while(set.size()!=2) {
				Thread.sleep(200);
				set = driver.getWindowHandles();
			}
			driver = driver.switchTo().window(set.toArray()[1].toString());
		}catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
	}
}
	/*
	 * This method will switch the driver control to new window*
	 */
	public static String windowHandle() {
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		return actualColor;
	}
	
	/*
	 * This method will switch the driver control back to parent window*
	 */
	public static void SwitchToParentWindow() throws Exception {
		try {
			set = driver.getWindowHandles();
			driver = driver.switchTo().window(set.toArray()[0].toString());
		}
		catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;	
			}
	}
	
	/*
	 * This method will switch the driver control back to new window*
	 */
	public static void windowHandleChild() {
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
	}
	
}
	public static void windowHandleParent(String parentWindow) {
			driver.switchTo().window(parentWindow);

}

	/*
	 * This method will close current instance of browser
	 */
	public static void close () throws Exception {
		try {
			driver.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
}


	/*
	 * This method will navigate back to old page
	 */
	public static void navigateBack () throws Exception {
		try {
			driver.navigate().back();
			simpleWait(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
}
	/*
	 * This method will check if given elementhas a hyperlink or not
	 */
	public static void validateNotAHyperlink (WebElement element, long timeOut) throws Exception {
		try {
			String value = element.getAttribute("href");
			if(value == null || value =="") {
				Reporter.addStepLog("\"" + element.getText().trim() + "\"" + "is not a hyperlink");
			}else {
				Assert.fail("\"" +element.getText().trim() + "\"" + " is a hyperlink");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}

	/*
	 * This method will check if given element has a hyperlink or not
	 */
	public static void validateIsAHyperlink (WebElement element, long timeOut) throws Exception {
		try {
			String value = element.getAttribute("href");
			if(value != null || value !="") {
				Reporter.addStepLog("\"" + element.getText().trim() + "\"" + "is a hyperlink");
			}else {
				Assert.fail("\"" +element.getText().trim() + "\"" + " is not a hyperlink");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	/*
	 * This method will check if given element has a dropdown list or not
	 */
	public static void validateNotADropDown (WebElement element, long timeOut, String textToDisplay) throws Exception {
		try {
			if(!(element.getTagName().equals("select"))) {
				Reporter.addStepLog("\"" + textToDisplay + "\"" + "is not a dropdown");
			}else {
				Assert.fail("\"" + textToDisplay + "\"" + " has a dropdown list");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	/*
	 * This method will press enter key
	 */
	public static void pressEnterKey (WebElement element, Duration timeOut) throws Exception {
		try {
			if(isElementVisible(element, timeOut)) {
				element.sendKeys(Keys.ENTER);
			}else {
				Assert.fail("Unable to find the field identified by = " + element);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	/*
	 * This method will accept the alert pop up window 
	 */
	public static void acceptPopUpAlert () throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	/*
	 * This method will launch the given url
	 */
	public static void launchTheUrl (String url) throws Exception {
		try {
			driver.get(url);
			Reporter.addStepLog("Launched the url = " + url);
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		}
		}
	
	/*
	 * This method will compare the color of given elemtn with expected
	 */
	public static void validateColor (WebElement element, String expectedColor, String textToDisplay, long timeOut) throws Exception {
		String actualHexColorValue = null; 
		try {
			actualColor = element.getCssValue("color");
			actualHexColorValue = Color.fromString(actualColor).asHex();
			if(expectedColor.equals(actualHexColorValue)) {
				Reporter.addStepLog("The color of the element is " + textToDisplay + "whose hexa value is = " +actualHexColorValue);
				System.out.println("The color of the element is " + textToDisplay + " whose hexa value is = " +actualHexColorValue);
			}else {
				Assert.fail("There is mismatch in the colors" + "Expected color = " + expectedColor + "Actual Color =" +actualHexColorValue);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	/*
	 * This method will get attribute value corresponding to given attribute
	 */
	public static String getAttribute (final WebElement element, String attributeName, final Duration timeOut) throws Exception {
		String attributeValue = null; 
		try {
			if(isElementVisible(element, timeOut)) {
				attributeValue = element.getAttribute(attributeName);
				return attributeValue;
				}else {
				Assert.fail("Failed to get the value corresponding to given attribute");
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		}
		}
	
	
	/*This method will validate attribute value is matching with expected value
	 * */
	
	public static void validateAttribute (final WebElement element, String attributeName, final Duration timeOut,String expectedValue) throws Exception {
		String actualValue = null;
		try {
			actualValue = getAttribute(element, attributeName, timeOut).trim();
			System.out.println("actualValue :"+actualValue);
			if(actualValue.equals(expectedValue)) {
				Reporter.addStepLog(actualValue + "is present");
			}else {
				Assert.fail("value mismatch" + "the expected value is " + "\"" + expectedValue + "\"" + " and the actual value is "+ "\"" +actualValue + "\"");
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		}
		}
	/*This method will get the text value and will validate if actual value contains expected value
	 * */
	
	public static void validatePartialText (WebElement element, String expectedText, final Duration timeOut) throws Exception {

		try {
			String actualText = getText(element, timeOut);
			if(actualText.contains(expectedText)) {
				Reporter.addStepLog("\""+actualText + "\"" + "is present");
			}else {
				Assert.fail("value mismatch" + "the expected value is " + "\"" + expectedText + "\"" + " and the actual value is "+ "\"" +actualText + "\"");
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		}
		}
	
	/*This method will will hover over a given element and will check on it using actions class
	 * */
	
	public static void hoverOver (final WebElement element, final String textToDisplay, final Duration timeOut) throws Exception {

		try {
			if(isElementVisible(element, timeOut)) {
				Actions builder = new Actions(driver);
				builder.moveToElement(element).build().perform();
				simpleWait(1000);
				Reporter.addStepLog("as Expected, hovered to" + textToDisplay + "successfully");
			}else {
				Assert.fail("failed to hover" + textToDisplay );
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		}
		}
	
	public static void underline (final WebElement element, final String textToDisplay, final Duration timeOut) throws Exception {

		try {
			if(isElementVisible(element, timeOut)) {
				element.getCssValue("text-decoration");
				simpleWait(1000);
				Reporter.addStepLog("as Expected," + textToDisplay + "is underlined");
			}else {
				Assert.fail("failed to hover" + textToDisplay );
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		}
		}
	/*Navigate forward*/
	
	public static void navigateForward() throws Exception {
		try {
			driver.navigate().forward();
			simpleWait(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*This method will check whether given element is present
	 * */
	
	public static void isElementNotPresent (final WebElement element, final String textToDisplay, final long timeOut) throws Exception {

		try {
			element.isDisplayed();
			Assert.fail("As Expected, " +textToDisplay + " is present");
		}catch(final Exception e) {
			Reporter.addStepLog("As expected, "+textToDisplay + "is not displayed");
		}
		}
	/*This method will refresh
	 * */
	
	public static void refresh () throws Exception {

		try {
			driver.navigate().refresh();
			simpleWait(2000);
		}catch(final Exception e) {
			e.printStackTrace();
		}
		}
	
	/*this method will get title
	 * */
	public static void validatePageTitle (String expectedTitle) throws Exception {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
			if(pageTitle.equals(expectedTitle)) {
				Reporter.addStepLog(pageTitle + "is present");
			}else {
				Assert.fail("there is mismatch. the actual page title is " + "/" +pageTitle+ "/" + "and he expected page title is " + "/" +expectedTitle+ "/" );
			}
		}catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw e;
		}
	}
		/*this method will check if given elemenr has drop down list
		 * */
		public static void validateIsDropDown (WebElement element, String textToDisplay, long timeOut) throws Exception {
			try {
				if(element.getTagName().equals("select")) {
					Reporter.addStepLog("\"" + textToDisplay + "is dropdown");
				}else {
					Assert.fail("\"" +textToDisplay+ "\"" + "is not a dropdown");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			public static boolean isAlertPresent () throws Exception {
				boolean foundAlert = false;
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
				try {
					wait.until(ExpectedConditions.alertIsPresent());
					foundAlert = true;
				}catch(TimeoutException eTO) {
					foundAlert = false;
				}
				return foundAlert;
			}
	
			public static void print (String string) {
				Reporter.addStepLog(string);

			}
			public static String getValue (final WebElement element, final long timeOut) throws Exception {
				return element.getAttribute(placeholder).trim();
			}
			public static boolean waitForpageLoad2 (long timeOut) {
				try {
					Timeouts timeOuts = driver.manage().timeouts();
					timeOuts.pageLoadTimeout(timeOut, TimeUnit.SECONDS);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return false;
			}

			public static void jescroll(WebElement e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", e);
			}
	
	public static void scrollDownFully() throws Exception{
		
	}
			
			
}