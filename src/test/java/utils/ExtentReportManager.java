package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtentReportManager {
    private static ExtentReports extent;
    public static ExtentReports getReportInstance() {

        if (extent == null) {

            try {
                Files.createDirectories(Paths.get("reports"));
            } catch (Exception e) {
                throw new RuntimeException("Unable to create reports folder", e);
            }

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("reports/AutomationReport.html");

            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("SauceDemo Test Execution");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "SauceDemo");
            extent.setSystemInfo("Automation Tool", "Selenium");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Test Framework", "TestNG");
        }

        return extent;
    }
}