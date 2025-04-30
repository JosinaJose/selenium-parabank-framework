package testCases;

import org.testng.annotations.Test;
import base.BaseClass;
import pageObjects.CreateAnAccoutPage;

public class CreateAnAccount extends BaseClass {

    @Test
    public void signInSetup() {
    	CreateAnAccoutPage page = new CreateAnAccoutPage(driver);
        page.clickMyAccountButton();
        page.clickCreateAnAccount();
        
        String email = "test@example.com";
        String password = "securePassword123";
        
        page.emailAddressInputField(email);
        page.passwordInputFeild(password);
        page.submitForm();
    }
}