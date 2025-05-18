package web.utilities.driver_manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utiltites.logger.Log4JLogger;
import web.utilities.exception_handling.ExceptionHandling;
import web.utilities.reader_manager.properties_reader.ConfigUtils;

import java.time.Duration;
import java.util.Objects;

/**
 * Manages WebDriver instances in a thread-safe manner.
 * Supports multiple browsers and headless configuration.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initializes a WebDriver based on the provided browser name and headless config.
     *
//     * @param browser Name of the browser to initialize ("chrome", "firefox", or "edge").
     */
    public static void initializeDriver() {
        boolean isHeadless = Boolean.parseBoolean(ConfigUtils.get_BrowserHeadlessType());

        String browser = ConfigUtils.get_BrowserType();
        Log4JLogger.logINFO(DriverManager.class, "Initializing driver for browser: " + browser + ", headless: " + isHeadless);

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                    }
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("-headless");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (isHeadless) {
                        edgeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                default:
                    Log4JLogger.logERROR(DriverManager.class, "Unsupported browser: " + browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            maximizeWindow();
            Log4JLogger.logINFO(DriverManager.class, "Driver initialized and window maximized successfully");

        } catch (Exception e) {
            Log4JLogger.logERROR(DriverManager.class, "Driver initialization failed for browser: " + browser);
            ExceptionHandling.handleException(e);
        }
    }

    /**
     * Maximizes the browser window and sets a default implicit wait.
     */
    public static void maximizeWindow() {
        try {
            Objects.requireNonNull(getDriver()).manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            Log4JLogger.logINFO(DriverManager.class, "Window maximized and implicit wait set");
        } catch (Exception exception) {
            Log4JLogger.logERROR(DriverManager.class, "Failed to maximize window or set implicit wait");
            ExceptionHandling.handleException(exception);
        }
    }

    /**
     * Navigates the browser to the given URL.
     *
     * @param url URL to navigate to.
     */
    public static void navigate(String url) {
        try {
            Log4JLogger.logINFO(DriverManager.class, "Navigating to URL: " + url);
            Objects.requireNonNull(getDriver()).navigate().to(url);
        } catch (Exception exception) {
            Log4JLogger.logERROR(DriverManager.class, "Failed to navigate to URL: " + url);
            ExceptionHandling.handleException(exception);
        }
    }

    /**
     * Returns the current thread's WebDriver instance.
     *
     * @return WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits the browser and removes the driver instance from the thread-local storage.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            Log4JLogger.logINFO(DriverManager.class, "Quitting driver");
            driver.get().quit();
            driver.remove();
            Log4JLogger.logINFO(DriverManager.class, "Driver quit and removed from thread");
        }
    }
}
