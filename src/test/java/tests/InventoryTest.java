package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    @Test(priority = 1)
    public void verifyProductsSortedByPriceLowToHigh() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.sortByPriceLowToHigh();

        List<Double> actualPrices =
                inventoryPage.getProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        Assert.assertEquals(
                actualPrices,
                expectedPrices,
                "Products are not sorted by price"
        );
    }
}
