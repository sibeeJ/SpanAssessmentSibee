package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String captureScreenshot(
            WebDriver driver, String testName) {
        try {

            Path folder = Paths.get("screenshots");
            Files.createDirectories(folder);

            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );

            String fileName = testName + "_" + timestamp + ".png";

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path destination = folder.resolve(fileName);

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("Screenshot saved: " + destination);

            return destination.toString();

        } catch (Exception e) {

            System.out.println("Unable to capture screenshot: "
                    + e.getMessage());

            return null;
        }
    }
}
