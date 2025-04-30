package base;

import java.util.HashMap;
import java.util.Map;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	private static final Map<String, Supplier<WebDriver>> BROWSER_MAP = new HashMap<>();

	static {
		BROWSER_MAP.put("chrome", new Supplier<WebDriver>() {
			@Override
			public WebDriver get() {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setAcceptInsecureCerts(true);
				return new ChromeDriver(chromeOptions);
			}
		});
		BROWSER_MAP.put("firefox", new Supplier<WebDriver>() {
			@Override
			public WebDriver get() {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setAcceptInsecureCerts(true);
				return new FirefoxDriver(firefoxOptions);
			}
		});
		BROWSER_MAP.put("edge", new Supplier<WebDriver>() {
			@Override
			public WebDriver get() {
				WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setAcceptInsecureCerts(true);
				return new EdgeDriver(edgeOptions);
			}

		});
	}

	public static WebDriver getDriver(String browser) {
		Supplier<WebDriver> driverSupplier = BROWSER_MAP.get(browser.toLowerCase());
		if (driverSupplier != null) {
			return driverSupplier.get();
		} else {
			throw new IllegalArgumentException("Unsupported Browser: " + browser);

		}

	}

}
