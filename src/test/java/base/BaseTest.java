package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utils.ConfigReader;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected Properties prop;

    @BeforeMethod
    public void setUp() {
        prop = ConfigReader.getProperties();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}