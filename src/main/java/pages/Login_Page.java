package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.core.GenericFunctions;

public class Login_Page extends GenericFunctions {
	
public Login_Page(WebDriver driver) {
	super(driver);
}
@FindBy(xpath = "//img[@alt='signInByMailButton']")
public static WebElement SignInbtn;
@FindBy(xpath = "//input[@placeholder='Enter Email Address']")
public static WebElement Enteremailaddress;
@FindBy(xpath = "//button[@data-cy='continueBtn']")
public static WebElement Continuebtn;
@FindBy(xpath = "//div[contains(@class,'modalFieldInner makeFlex')]//input[1]")
public static WebElement EnterOtp;
@FindBy(xpath = "(//span[@class='headerIconWrapper']//span)[2]")
public static WebElement Hoteloption;

@FindBy(xpath = "//span[@class='commonModal__close']")
public static WebElement CloseLogin;
@FindBy(xpath = "//li[@data-cy='menu_Flights']")
public static WebElement FlightMenu;

}
