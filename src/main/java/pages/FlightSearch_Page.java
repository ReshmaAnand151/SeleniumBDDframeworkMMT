package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.core.GenericFunctions;

public class FlightSearch_Page extends GenericFunctions {
	
public FlightSearch_Page(WebDriver driver) {
	super(driver);
}
@FindBy(xpath = "//li[@data-cy='oneWayTrip']")
public static WebElement OneWayTrip;
@FindBy(xpath = "//input[@data-cy='fromCity']")
public static WebElement FromCity; 
@FindBy(xpath = "//input[@placeholder='From']")
public static WebElement FromCityTextfield;
@FindBy(xpath = "//input[@data-cy='toCity']")
public static WebElement ToCity;
@FindBy(xpath = "//input[@placeholder='To']")
public static WebElement ToCityTextfield;
@FindBy(xpath = "//p[@data-cy='submit']")
public static WebElement Searchbutton;



}
