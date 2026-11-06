package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class TestListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static Map<ITestResult, ExtentTest> testMap =
            new ConcurrentHashMap<>();

    @Override
    public void onStart(ISuite suite) {

        extent = ExtentReportManager.getReportInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName()
        );

        testMap.put(result, test);

        test.log(Status.INFO, "Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        testMap.get(result).log(
                Status.PASS,
                "Test case passed successfully"
        );

        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = testMap.get(result);

        test.log(Status.FAIL, "Test case failed");
        test.log(Status.FAIL, result.getThrowable());

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {

            BaseTest baseTest = (BaseTest) testClass;

            if (baseTest.driver != null) {

                String screenshotPath = ScreenshotUtil.captureScreenshot(
                        baseTest.driver,
                        result.getMethod().getMethodName()
                );

                if (screenshotPath != null) {

                    try {
                        test.addScreenCaptureFromPath(screenshotPath);
                    } catch (Exception e) {
                        test.log(Status.WARNING,
                                "Unable to attach screenshot to report");
                    }
                }
            }
        }

        System.out.println("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = testMap.get(result);

        if (test != null) {
            test.log(Status.SKIP, "Test case was skipped");
        }
    }

    @Override
    public void onFinish(ISuite suite) {

        if (extent != null) {
            extent.flush();
        }

        System.out.println("Extent report generated successfully");
    }
}
