package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1)
    public void verifyCompleteCheckoutFlow() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");

        inventoryPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCustomerDetails(
                "John",
                "Doe",
                "12345"
        );

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "Order confirmation message is incorrect"
        );
    }

    @Test(priority = 2)
    public void verifyCheckoutWithEmptyFirstName() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        inventoryPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCustomerDetails(
                "",
                "Doe",
                "12345"
        );

        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: First Name is required",
                "First Name validation message is incorrect"
        );
    }
}
