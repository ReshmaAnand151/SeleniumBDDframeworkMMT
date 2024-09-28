package Webapp;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.core.GenericFunctions;
import utility.core.Util;

public class MMT_Login_01
{
	@Given("^User is in signin page$")
			public void user_is_in_signin_page() throws Throwable {
			// Write code here that turns the phrase above into concrete actions
			//Hooks. launchUr1QA();
			Hooks.driverSetup();
	}
	/*
	 * @And("^\"([^\"]*)\" enter \"([^\"]*)\" and \"([^\"]*)\" in page$") public
	 * void user_enter_username_and_password_in_page(String User, String username,
	 * String password) throws Throwable {
	 * 
	 * // Write code here that turns the phrase above into concrete actions //Hooks.
	 * launchUr1QA(); }
	 */
	@When("^Close Login into page$")
	public void Close_Login_into_page() throws Throwable {
		pages.Login_Page.CloseLogin.click(); 
		Reporter.addScreenCaptureFromPath(Util.takeScreenshtFile(GenericFunctions.driver).toString());
}
	@And("^User selects onewaytrip$")
	public void user_selects_onewaytrip() throws Throwable {
	
		JavascriptExecutor js = (JavascriptExecutor) GenericFunctions.driver;
		js.executeScript("arguments[0].click();", pages.Login_Page.FlightMenu);
	
}

	@Then("^User should be navigated into the Flight page successfully$")
	public void user_should_be_navigated_into_the_Flight_page_successfully() throws Throwable {
	// Write code here that turns the phrase above into concrete actions
	//Hooks. launchUr1QA();
}
	@Then("^IFRAMES$")
	public void IFRAMES() throws Throwable {

	//switching parent 
		GenericFunctions.driver.switchTo().frame(GenericFunctions.driver.findElement(By.xpath("")));
		GenericFunctions.driver.switchTo().frame(GenericFunctions.driver.findElement(By.xpath("")));

		GenericFunctions.switchTotheLastWindow();
		
		GenericFunctions.driver.switchTo().frame(GenericFunctions.driver.findElement(By.xpath("")));
		GenericFunctions.driver.switchTo().defaultContent();

	}
}