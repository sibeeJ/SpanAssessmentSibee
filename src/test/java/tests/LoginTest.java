package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                prop.getProperty("username"),
                prop.getProperty("password")
        );
        String actualTitle = driver.getTitle();
        Assert.assertEquals(
                actualTitle,
                "Swag Labs",
                "Login Unsuccessful"
        );
        String actualHeading =
                driver.findElement(org.openqa.selenium.By.className("title")).getText();
        Assert.assertEquals(
                actualHeading,
                "Products",
                "Inventory page was not displayed"
        );
    }

    @Test(priority = 2)
    public void verifyLockedUserLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(
                actualError.contains("locked out"),
                "Locked User Error was not displayed"
        );
    }
}