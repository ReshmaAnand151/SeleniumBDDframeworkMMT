package Webapp;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;

import pages.FlightSearch_Page;
import pages.Login_Page;
import utility.properties.Property;

public class Hooks {

	public static WebDriver driver;
	static DesiredCapabilities capabilities;
	static String scenarioName;
	public static File f = new File(".");
	public static Properties prop;
	
	public static void driverSetup() throws IOException{
		// TODO Auto-generated method stub
		System.out.println("Inside driverset up");
		prop=new Properties();
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\seleniumframeworkproj\\Labvantgage\\config.properties");
		final FileInputStream fileinputStream=new FileInputStream(file);
		prop.load(fileinputStream);
		
		System.out.println("Browser is "+prop.getProperty("Browser"));
		switch (prop.getProperty("Browser")) {
		
		case "safari":
			driver = new SafariDriver();
			break;
			
		case "Firefox":
			FirefoxOptions options1 = new FirefoxOptions();
			options1.setAcceptInsecureCerts(true);
			options1.setPlatformName("Windows 10");
			driver.manage().deleteAllCookies();
			driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
		break;
		
		case "Chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			options.addArguments("start-maximized"); 
			options.addArguments("--ignore-certificate-errors"); 
			options.addArguments("--disable-popup-blocking"); 
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		//	driver.get("https://www.amazon.in");
			driver.get(Property.getProperty("UrlQA"));
			System.out.println("URL is entered");
			initializePageFactory();
			System.out.println("Initialize pages");
			break;
			
			case "hb":
				ChromeOptions options2 = new ChromeOptions();
				options2.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options2);
				options2.addArguments("start-maximized"); 
				options2.addArguments("--ignore-certificate-errors"); 
				options2.addArguments("--disable-popup-blocking"); 
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				break;
			default:
			break;		
		}
	}

	public static void initializePageFactory() {
		PageFactory.initElements(driver, Login_Page.class);
		PageFactory.initElements(driver, FlightSearch_Page.class);
	}
}
