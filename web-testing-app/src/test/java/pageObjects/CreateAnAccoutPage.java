package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class CreateAnAccoutPage extends BasePage {

	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions action;

	public CreateAnAccoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait with a 10-second timeout
		this.js = (JavascriptExecutor) driver;
		this.action = new Actions(driver);
	}

	@FindBy(xpath = "/html/body/div[1]/header/div/div[2]/div/div/div[3]/div[2]/a")
	public WebElement myAccountButton;
	@FindBy(xpath = "/html/body/div[5]/div[2]/a")
	public WebElement createAnAccount;

	@FindBy(xpath = "//*[@id=\"reg_email\"]")
	public WebElement emailAddressInputField;
	@FindBy(xpath = "//*[@id=\"reg_password\"]")
	public WebElement passwordInputField;
	@FindBy(xpath = "//*[@id=\"customer_login\"]/div[2]/form/p[3]/button")
	public WebElement registerButton;

	/* ================================================== */

	public void clickMyAccountButton() {
		// myAccountButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(myAccountButton)).click();
	}

	public void clickCreateAnAccount() {
		// createAnAccount.click();

		wait.until(ExpectedConditions.elementToBeClickable(createAnAccount)).click();
	}

	public void emailAddressInputField(String email) {

		wait.until(ExpectedConditions.visibilityOf(emailAddressInputField));
		emailAddressInputField.sendKeys(email);
	}

	public void passwordInputFeild(String password) {

		wait.until(ExpectedConditions.visibilityOf(passwordInputField));
		passwordInputField.sendKeys(password);
	}

	public void submitForm() {

		wait.until(ExpectedConditions.elementToBeClickable(registerButton));
	}

}
