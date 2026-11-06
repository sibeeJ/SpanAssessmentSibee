package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class LogoutTest extends BaseTest {

    @Test(priority = 1)
    public void verifyLogoutFunctionality() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.logout();

        boolean loginButtonDisplayed =
                driver.findElement(By.id("login-button")).isDisplayed();

        Assert.assertTrue(
                loginButtonDisplayed,
                "User was not redirected to login page"
        );
    }
}
