package web.utilities.exception_handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandling {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

    public static void handleException(Exception exception) {
        logger.error("Exception Happening: ", exception);
    }
}