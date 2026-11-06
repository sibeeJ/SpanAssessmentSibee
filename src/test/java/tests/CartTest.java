package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {

    @Test(priority = 1)
    public void verifySingleProductAddedToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                "1",
                "Cart count is incorrect"
        );

        inventoryPage.clickCart();
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isProductDisplayed("Sauce Labs Backpack"),
                "Product was not added to cart"
        );
    }

    @Test(priority = 2)
    public void verifyMultipleProductsAndRemoveOne() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                "3",
                "Cart count is incorrect"
        );

        inventoryPage.clickCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.waitForCartItemCount(3);
        cartPage.removeProduct("Sauce Labs Bike Light");
        cartPage.waitForCartItemCount(2);

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                2,
                "Expected two products in cart"
        );

        Assert.assertFalse(
                cartPage.isProductDisplayed("Sauce Labs Bike Light"),
                "Removed product is still displayed");

    }
}
