package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class LoginPage extends BasePage {

	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions action;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait with a 10-second timeout
		this.js = (JavascriptExecutor) driver;
		this.action = new Actions(driver);
	}

	//@FindBy(xpath = "//input[@name='username']")
	//public WebElement myAccountButton;

	@FindBy(xpath = "//input[@name='username']")
	public WebElement enterUserName;
	
	@FindBy(xpath = "//input[@name='password']")
	public WebElement enetPassword;
	
	@FindBy(xpath = "//input[@value='Log In']")
	public WebElement clickLoginButton;

	@FindBy(xpath = "/html/body/div[4]/div[1]/div[2]/ul[1]")
	public List<WebElement> homeTabs;

	

	public void enetrUserNameFiled(String username) {
		wait.until(ExpectedConditions.visibilityOf(enterUserName)).sendKeys(username);
	}

	public void enetrPasswordField(String password) {
		wait.until(ExpectedConditions.visibilityOf(enetPassword)).sendKeys(password);
	}

	public void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(clickLoginButton)).click();
	}

	public List<WebElement> getHomeTabs() {
		wait.withTimeout(Duration.ofSeconds(20));
		List<WebElement> tabs = wait.until(ExpectedConditions.visibilityOfAllElements(homeTabs));

		// Hover over each tab
		for (WebElement tab : tabs) {
			action.moveToElement(tab).perform();
		}

		return tabs;
	}

}
