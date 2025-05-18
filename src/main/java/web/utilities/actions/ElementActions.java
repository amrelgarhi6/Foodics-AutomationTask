package web.utilities.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utiltites.logger.Log4JLogger;
import web.utilities.driver_manager.DriverManager;
import web.utilities.exception_handling.ExceptionHandling;
import web.utilities.waits.Waits;

import java.util.List;
import java.util.Objects;

/**
 * Utility class for interacting with web elements.
 * Provides actions like finding elements, clicking, typing, scrolling, and dropdown selections.
 */
public class ElementActions {

    /**
     * Waits for the visibility of the element and returns it.
     *
     * @param elementLocator Locator of the element.
     * @return WebElement found using the locator.
     */
    public static WebElement findElement(By elementLocator) {
        try {
            Log4JLogger.logINFO(ElementActions.class, "Finding element: " + elementLocator);
            Waits.waitForElementToBeVisible(elementLocator);
        } catch (Exception exception) {
            Log4JLogger.logERROR(ElementActions.class, "Failed to find element: " + elementLocator);
            ExceptionHandling.handleException(exception);
        }
        return Objects.requireNonNull(DriverManager.getDriver()).findElement(elementLocator);
    }

    /**
     * Waits for visibility of all matching elements and returns the list.
     *
     * @param elementLocator Locator of the elements.
     * @return List of WebElements.
     */
    public static List<WebElement> findElements(By elementLocator) {
        try {
            Log4JLogger.logINFO(ElementActions.class, "Finding elements: " + elementLocator);
            Waits.waitForAllElementsToBeVisible(elementLocator);
        } catch (Exception exception) {
            Log4JLogger.logERROR(ElementActions.class, "Failed to find elements: " + elementLocator);
            ExceptionHandling.handleException(exception);
        }
        return Objects.requireNonNull(DriverManager.getDriver()).findElements(elementLocator);
    }

    /**
     * Selects an option from a dropdown by its visible text.
     *
     * @param dropdownLocator Locator of the dropdown element.
     * @param visibleText     Visible text of the option to be selected.
     */
    public static void selectDropdownByVisibleText(By dropdownLocator, String visibleText) {
        try {
            Log4JLogger.logINFO(ElementActions.class, "Selecting dropdown by visible text: " + visibleText);
            WebElement dropdown = findElement(dropdownLocator);
            Select select = new Select(Objects.requireNonNull(dropdown));
            select.selectByVisibleText(visibleText);
        } catch (Exception exception) {
            Log4JLogger.logERROR(ElementActions.class, "Failed to select dropdown: " + visibleText);
            ExceptionHandling.handleException(exception);
        }
    }

    /**
     * Waits for the element to be clickable and performs a click.
     *
     * @param elementLocator Locator of the element to click.
     */
    public static void click(By elementLocator) {
        try {
            Log4JLogger.logINFO(ElementActions.class, "Clicking element: " + elementLocator);
            Waits.waitForElementToBeClickable(elementLocator);
            findElement(elementLocator).click();
        } catch (Exception exception) {
            Log4JLogger.logERROR(ElementActions.class, "Failed to click element: " + elementLocator);
            ExceptionHandling.handleException(exception);
        }
    }

    /**
     * Performs a forced click on an element using JavaScript.
     * Useful when regular click doesn't work due to overlays or DOM issues.
     *
     * @param elementLocator Locator of the element to be clicked forcefully.
     */
    public static void forceClick(By elementLocator) {
        try {
            Log4JLogger.logINFO(ElementActions.class, "Force clicking element: " + elementLocator);
            Waits.waitForElementToBeClickable(elementLocator);
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", findElement(elementLocator));
        } catch (Exception exception) {
            Log4JLogger.logERROR(ElementActions.class, "Failed to force click element: " + elementLocator);
            ExceptionHandling.handleException(exception);
        }
    }

    /**
     * Scrolls to the specified element using JavaScript.
     *
     * @param locator Locator of the element to scroll into view.
     */
    public static void scrollToElement(By locator) {
        Log4JLogger.logINFO(ElementActions.class, "Scrolling to element: " + locator);
        WebElement element = findElement(locator);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Sends text to the specified element.
     *
     * @param elementLocator Locator of the input element.
     * @param text           Text to send.
     */
    public static void type(By elementLocator, String text) {
        try {
            Log4JLogger.logINFO(ElementActions.class, "Typing in element: " + elementLocator + " Text: " + text);
            findElement(elementLocator).sendKeys(text);
        } catch (Exception exception) {
            Log4JLogger.logERROR(ElementActions.class, "Failed to type in element: " + elementLocator);
            ExceptionHandling.handleException(exception);
        }
    }

    /**
     * Retrieves text content from a web element.
     *
     * @param elementLocator Locator of the element.
     * @return Text from the element, or null if an error occurs.
     */
    public static String getText(By elementLocator) {
        try {
            Log4JLogger.logINFO(ElementActions.class, "Getting text from element: " + elementLocator);
            String text = findElement(elementLocator).getText();
            Log4JLogger.logINFO(ElementActions.class, "Retrieved text: " + text);
            return text;
        } catch (Exception exception) {
            Log4JLogger.logERROR(ElementActions.class, "Failed to get text from element: " + elementLocator);
            ExceptionHandling.handleException(exception);
            return null;
        }
    }
}
