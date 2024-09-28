package Webapp;

import cucumber.api.java.en.And;

import utility.core.Util;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.description.type.TypeDescription.Generic;
import utility.core.GenericFunctions;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;

import Webapp.Hooks;


/*public class Login 
{
	@Given("^User is in signin page$")
			public void user_is_in_signin_page() throws Throwable {
			// Write code here that turns the phrase above into concrete actions
			//Hooks. launchUr1QA();
			Hooks.driverSetup();
	}
	
	 * @And("^\"([^\"]*)\" enter \"([^\"]*)\" and \"([^\"]*)\" in page$") public
	 * void user_enter_username_and_password_in_page(String User, String username,
	 * String password) throws Throwable {
	 * 
	 * // Write code here that turns the phrase above into concrete actions //Hooks.
	 * launchUr1QA(); }
	 
	@When("^Login into page$")
	public void Login_into_page() throws Throwable {
		pages.Login_Page.SignInbtn.click();
		pages.Login_Page.Enteremailaddress.sendKeys("reshmaanand151@gmail.com");
		 WebDriverWait wait = new WebDriverWait(GenericFunctions.driver,
		 Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.elementToBeClickable(pages.Login_Page.Continuebtn)); 
		 pages.Login_Page.Continuebtn.click();
		 
		JavascriptExecutor js = (JavascriptExecutor)GenericFunctions.driver;
		js.executeScript("argument[0].click()", pages.Login_Page.Continuebtn);
		
		//GenericFunctions.driver.findElement(By.xpath("//button[@data-cy='continueBtn']")).click();
		Scanner scanner_user;
		scanner_user = new Scanner(System.in);
		System.out.println("Enter the OTP: ");
		String user = scanner_user.nextLine();
		pages.Login_Page.EnterOtp.sendKeys(user);
		scanner_user.close();
		//GenericFunctions.driver.navigate().refresh();
		Thread.sleep(2000);
		Reporter.addScreenCaptureFromPath(Util.takeScreenshtFile(GenericFunctions.driver).toString());
}
	@And("^User click on contact sales$")
	public void user_click_on_contact_sales() throws Throwable {
	
		GenericFunctions.driver.findElement(By.xpath("(//a[contains(text(),'Contact Sales')])[1]")).click();
		WebElement videos = GenericFunctions.driver.findElement(By.xpath("//a[@title='How To Videos']"));
		JavascriptExecutor js = (JavascriptExecutor) GenericFunctions.driver;
		js.executeScript("arguments[0].click();", videos);
	
}

	@Then("^User should be logged into the page successfully$")
	public void user_should_be_logged_into_the_page_successfully() throws Throwable {
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
	*/

