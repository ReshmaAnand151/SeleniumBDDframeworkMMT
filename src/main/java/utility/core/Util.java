package utility.core;

import java.io.File;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Util {

	private Util() {
		
	}
	
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}
	public static String getAbsolutePath() {
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		return relativePath;
	}
	public static String getResultsPath() {
		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target");
		if(!path.isDirectory()) {
			path.mkdirs();
		}
		return path.toString();
	}
	
	public static String getOldResultPath() {
		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "ResultsOld");
		if(!path.isDirectory()) {
			path.mkdirs();
		}
		return path.toString();
	}
	public static String getTargetPath() {
		File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator() + "cucumber-report");
		return targetPath.toString();
	}
	public static String getAllurePath() {
		File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator() + "site");
		return targetPath.toString();
	}
	public static byte[] takeScreenshot(WebDriver driver) {
		if(driver == null) {
			throw new RuntimeException("Report.driver is not initialized!");
		}
		if(driver.getClass().getSimpleName().equals("HtmlUnitDriver") || driver 
		.getClass()
		.getGenericSuperclass()
		.toString()
		.equals("class.org.openqa.selenium.htmlunit.HtmlUnitDriver")){
			return null;
		}
		if(driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
			Capabilities capabilities = ((RemoteWebDriver) driver)
					.getCapabilities();
			if(capabilities.getBrowserName().equals("htmlunit")) {
				return null;
			}
			WebDriver augumentedDriver = new Augmenter().augment(driver);
			return ((TakesScreenshot) augumentedDriver).getScreenshotAs(OutputType.BYTES);
		}else {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES); 
		}
	}
/**Function to returnthe current Time**/
	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
/**Function to returnthe current Time formated as per dateformatstring setting**/
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}
	/**Function to format the given time variable as specified by the DateFormatString setting**/
	public static String getFormattedTime(Date time, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}		
	/**Function to get the time difference between 2 @link date variables in minutes/seconds**/
	public static String gettimeDifference(Date startTime, Date endTime) {
		long timeDifferenceSeconds = (endTime.getTime() - startTime.getTime()) / 1000;
		
		long timeDifferenceMinutes = timeDifferenceSeconds/60;
		
		String timeDifferenceDetailed;
		if(timeDifferenceMinutes >= 60) {
			long timeDifferenceHours = timeDifferenceMinutes / 60;
			
			timeDifferenceDetailed = Long.toString(timeDifferenceHours)
					+ "hour(s), " + Long.toString(timeDifferenceMinutes % 60)
					+ "minute(s), " + Long.toString(timeDifferenceSeconds % 60) + "second(s)";
		}else {
			timeDifferenceDetailed = Long.toString(timeDifferenceMinutes) + "minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + "second(s)";
		}
		return timeDifferenceDetailed;
	}		
	public static String takeScreenshtFile(WebDriver driver) {
		String screenShotPath = null;
		try {
			if(driver == null) {
				throw new RuntimeException("Report.driver is not initialized!");
			}
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			screenShotPath = copyFile(scrFile).toString();
		}catch(Exception e) {
			
		}return screenShotPath;
	}
	
	private static File copyFile(File scrFile) {
		File newPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "Screenshots");
		if(!newPath.isDirectory()) { 
			newPath.mkdir();
		}
		File screenShotPath = new File(
				newPath + Util.getFileSeparator() + RandomStringUtils.randomAlphanumeric(16) + ".png");
		try {
			FileUtils.copyFile(scrFile, new File(screenShotPath.toString()), true);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return screenShotPath;
	}	
}
