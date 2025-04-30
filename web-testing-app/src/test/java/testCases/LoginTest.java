package testCases;

import org.testng.annotations.Test;
import base.BaseClass;
import pageObjects.LoginPage;

public class LoginTest extends BaseClass {
    
    @Test
    public void loginModuleTest() {  // Removed parameters
        LoginPage page = new LoginPage(driver);  // Use driver from BaseClass
        
        page.enetrUserNameFiled("saramc");
        page.enetrPasswordField("saramc");
        page.clickLoginButton();
    }
}