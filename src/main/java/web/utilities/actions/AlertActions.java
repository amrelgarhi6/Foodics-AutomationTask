package web.utilities.actions;

import utiltites.logger.Log4JLogger;
import web.utilities.driver_manager.DriverManager;
import web.utilities.exception_handling.ExceptionHandling;
import web.utilities.waits.Waits;

import java.util.Objects;

/**
 * Utility class for handling JavaScript alert interactions on web pages.
 * Includes methods to accept alerts and retrieve alert text.
 */
public class AlertActions {

    /**
     * Waits for an alert to be present and accepts it.
     * Useful for confirming or dismissing alert messages during automation.
     */
    public static void acceptAlert() {
        try {
            Log4JLogger.logINFO(AlertActions.class, "Attempting to accept alert");
            Waits.waitForAlertToBeVisible();
            Objects.requireNonNull(DriverManager.getDriver()).switchTo().alert().accept();
            Log4JLogger.logINFO(AlertActions.class, "Alert accepted successfully");
        } catch (Exception exception) {
            Log4JLogger.logERROR(AlertActions.class, "Failed to accept alert");
            ExceptionHandling.handleException(exception);
        }
    }

    /**
     * Waits for an alert to be present and retrieves its message text.
     * Can be used for validation or debugging alert contents.
     *
     * @return The text displayed in the alert, or null if an exception occurs.
     */
    public static String getAlertText() {
        try {
            Log4JLogger.logINFO(AlertActions.class, "Attempting to retrieve alert text");
            Waits.waitForAlertToBeVisible();
            String alertText = Objects.requireNonNull(DriverManager.getDriver()).switchTo().alert().getText();
            Log4JLogger.logINFO(AlertActions.class, "Retrieved alert text", alertText);
            return alertText;
        } catch (Exception exception) {
            Log4JLogger.logERROR(AlertActions.class, "Failed to retrieve alert text");
            ExceptionHandling.handleException(exception);
            return null;
        }
    }
}
