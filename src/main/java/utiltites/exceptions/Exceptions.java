package utiltites.exceptions;

import utiltites.logger.Log4JLogger;

import java.util.Arrays;

public class Exceptions {
    public static void handle(Class<?> c, Exception exception) {
//        Log4JLogger.logERROR(c, "Error message: " + exception.getMessage());
//        Log4JLogger.logERROR(c, "Error Cause: " + exception.getCause());
        Log4JLogger.logERROR(c, "StackTrace: " + Arrays.toString(exception.getStackTrace()) + "Error Cause: " + exception.getCause());
    }
}