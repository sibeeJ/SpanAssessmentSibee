package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    WebDriver driver;
    WebDriverWait wait;

    By cartIcon = By.className("shopping_cart_link");
    By cartCount = By.className("shopping_cart_badge");
    By sortDropdown = By.className("product_sort_container");
    By menuButton = By.id("react-burger-menu-btn");
    By logoutButton = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void addProductToCart(String productName) {

        String productXpath =
                "//div[contains(@class,'inventory_item')]"
                        + "[.//div[contains(@class,'inventory_item_name')"
                        + " and normalize-space()='" + productName + "']]"
                        + "//button";

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(productXpath)
                )
        );
        button.click();
        wait.until(ExpectedConditions.textToBe(
                By.xpath(productXpath), "Remove"
        ));
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getText();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }

    public List<Double> getProductPrices() {

        List<Double> prices = new ArrayList<>();
        List<org.openqa.selenium.WebElement> priceElements =
                driver.findElements(By.className("inventory_item_price"));

        for (org.openqa.selenium.WebElement element : priceElements) {
            String priceText = element.getText();
            double price = Double.parseDouble(
                    priceText.replace("$", "")
            );
            prices.add(price);
        }
        return prices;
    }

    public void sortByPriceLowToHigh() {

        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue("lohi");
    }

    public void logout() {

        driver.findElement(menuButton).click();
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(logoutButton)
        );
        button.click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("login-button")
        ));
    }
}
