package Webapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;

import com.cucumber.listener.Reporter;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.core.GenericFunctions;
import utility.core.Util;
import utility.properties.Property;

public class MMT_SearchFlight_02 {

	@Given("^User is in Flight search page$")
	public void User_is_in_Flight_search_page() throws Throwable {
		pages.FlightSearch_Page.OneWayTrip.click();
	
}
	@When("^Enter From and To City$")
	public void Enter_From_and_To_City() throws Throwable {
		pages.FlightSearch_Page.FromCity.click();
		pages.FlightSearch_Page.FromCityTextfield.sendKeys(Property.getProperty("FromCity"));
		pages.FlightSearch_Page.ToCity.click();
		pages.FlightSearch_Page.ToCityTextfield.sendKeys(Property.getProperty("ToCity"));
		Reporter.addScreenCaptureFromPath(Util.takeScreenshtFile(GenericFunctions.driver).toString());
}
	@And("^User Enters Date of travel$")
	public void User_Enters_Date_of_travel() throws Throwable {
	
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE, 2);
	    String newDate = dateFormat.format(cal.getTime());
	    System.out.println(newDate);
//		JavascriptExecutor js = (JavascriptExecutor) GenericFunctions.driver;
//		js.executeScript("arguments[0].click();", pages.Login_Page.FlightMenu);
	
}
	@Then("^Flight search results should be displayed$")
	public void Flight_search_results_should_be_displayed() throws Throwable {
	
//		JavascriptExecutor js = (JavascriptExecutor) GenericFunctions.driver;
//		js.executeScript("arguments[0].click();", pages.Login_Page.FlightMenu);
		GenericFunctions.driver.close();
	
}

}
