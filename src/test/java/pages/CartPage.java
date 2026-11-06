package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductDisplayed(String productName) {

        String productXpath =
                "//div[contains(@class,'cart_item')]"
                        + "//div[contains(@class,'inventory_item_name')"
                        + " and normalize-space()='" + productName + "']";

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(productXpath)
            ));

            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }

    public void removeProduct(String productName) {

        String productXpath =
                "//div[contains(@class,'cart_item')]"
                        + "[.//div[contains(@class,'inventory_item_name')"
                        + " and normalize-space()='" + productName + "']]";

        String removeXpath = productXpath + "//button";
        WebElement removeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(removeXpath)
                )
        );
        removeButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath(productXpath)
        ));
    }

    public int getCartItemCount() {
        return driver.findElements(
                By.className("cart_item")
        ).size();
    }

    public void waitForCartItemCount(int expectedCount) {

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("cart_item"),
                expectedCount
        ));
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        driver.findElement(checkoutButton).click();
    }
}
