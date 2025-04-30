package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseClass {
	public WebDriver driver;
	private ReadConfig configReader;

	// Initialize logger
	public static Logger logger;

	@BeforeClass
	@Parameters("browser")
	public void setup(@Optional("chrome") String browser) {
		configReader = new ReadConfig();

		// Use the parameter from TestNG or fallback to "chrome" if it's null/empty
		if (browser == null || browser.isEmpty()) {
			browser = "chrome"; // default to chrome if no browser is passed
		}

		// Use BrowserFactory to get the appropriate WebDriver based on the passed
		// browser parameter
		driver = BrowserFactory.getDriver(browser.toLowerCase());

		// Initialize Logger configuration
		logger = Logger.getLogger("Demo Webshop");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		logger.debug("Debug logging has started");

		// Maximize the browser window
		driver.manage().window().maximize();

		// Get the URL from config file and navigate
		String url = configReader.getProperty("url");
		logger.info("Navigating to URL: " + url);
		driver.get(url);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Method to capture screenshot
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Reports\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot taken for test: " + tname + " at location: " + target.getAbsolutePath());
	}

	// Utility methods for random string, number, and email generation
	public static String randomestring() {
		return org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5);
	}

	public static String randomeNum() {
		return org.apache.commons.lang3.RandomStringUtils.randomNumeric(4);
	}

	public static String randomemail() {
		return randomestring() + "@gmail.com";
	}

}
