package web.utilities.hooks;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utiltites.cli.CLI;
import utiltites.logger.Log4JLogger;
import web.utilities.driver_manager.DriverManager;
import web.utilities.exception_handling.ExceptionHandling;
import web.utilities.helper.Helper;
import web.utilities.reader_manager.properties_reader.ConfigUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Handles WebDriver setup and teardown for each test scenario using TestNG and Cucumber.
 * Captures screenshots for both passed and failed tests and attaches them to Allure reports.
 */
public class Hooks extends AbstractTestNGCucumberTests {

    /**
     * Sets up the WebDriver instance before each test method.
     */
//    @Parameters("browser")
    @BeforeMethod
    public void setUpDriver() {
        try {
//            DriverManager.initializeDriver(browser);
            DriverManager.initializeDriver();
            String url = ConfigUtils.get_PortalURL();
            DriverManager.navigate(url);
            Log4JLogger.logINFO(Hooks.class, "Navigated to URL: " + url);
        } catch (Exception e) {
            Log4JLogger.logERROR(Hooks.class, "Error during driver setup.");
            ExceptionHandling.handleException(e);
        }
    }

    /**
     * Tears down the WebDriver instance after each test method.
     * Captures and attaches screenshots and logs to Allure.
     *
     * @param result ITestResult instance containing test result metadata.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownDriver(ITestResult result) {
        try {
            Log4JLogger.logINFO(Hooks.class, "Tearing down driver for test: " + result.getName());
            captureAndAttachArtifacts(result);
        } catch (Exception e) {
            Log4JLogger.logERROR(Hooks.class, "Error during driver teardown.");
            ExceptionHandling.handleException(e);
        } finally {
            DriverManager.quitDriver();
        }
    }

    /**
     * Captures screenshot and attaches relevant logs to Allure based on the test result.
     *
     * @param result ITestResult instance containing test result metadata.
     */
    private void captureAndAttachArtifacts(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        String testName = result.getName();

        try {
            // Optional file-based screenshot for local review
            String status = result.getStatus() == ITestResult.FAILURE ? "Failed" : "Passed";
            Helper.captureScreenshot(driver, testName + "-" + status);

            // Attach screenshot as bytes to Allure
            byte[] screenshotBytes = Helper.captureScreenshotAsBytes(driver);
            Allure.addAttachment(status + " Screenshot", new ByteArrayInputStream(screenshotBytes));

            // Attach log message to Allure
            if (result.getStatus() == ITestResult.FAILURE) {
                Log4JLogger.logERROR(Hooks.class, "Test FAILED: " + testName);
                Allure.addAttachment("Failure Reason", String.valueOf(result.getThrowable()));
            } else {
                Log4JLogger.logINFO(Hooks.class, "Test PASSED: " + testName);
                Allure.addAttachment("Test Status", "Passed Successfully");
            }

        } catch (IOException e) {
            Log4JLogger.logERROR(Hooks.class, "Error while capturing screenshot or attaching logs.");
            ExceptionHandling.handleException(e);
        }
    }
    @BeforeSuite(alwaysRun = true)
    public void deleteOutDatedAllureReport() {
        Log4JLogger.logINFO(Hooks.class, "Deleting old Allure results...");
        CLI.executeCommandLine("rmdir /S /Q allure-results");
    }

    @AfterSuite(alwaysRun = true)
    public void generateAllureReport() {
        if (ConfigUtils.isAllureReportGenerationEnabled()) {
            Log4JLogger.logINFO(Hooks.class, "Generating Allure report automatically...");
            CLI.executeCommandLine("allure generate --clean allure-results -o allure-report");
            CLI.executeCommandLine("allure open allure-report");
        } else {
            Log4JLogger.logINFO(Hooks.class, "Skipping Allure report generation (flag is false)");
        }
    }
}
