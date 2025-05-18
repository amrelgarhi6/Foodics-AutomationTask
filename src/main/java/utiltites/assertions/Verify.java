package utiltites.assertions;


import utiltites.exceptions.Exceptions;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import utiltites.logger.Log4JLogger;
import org.testng.asserts.SoftAssert;

public class Verify {
    public Verify() {
    }

    static SoftAssert softAssert = new SoftAssert();



    public Verify textToBe(@NotNull final String actualText, @NotNull final String expectedText) {
        try {
            softAssert.assertEquals(actualText, expectedText);
            Log4JLogger.logINFO(Verify.class, new Object() {}.getClass().getEnclosingMethod().getName(), "Actual text {" + actualText + "} is equals to the expected text {" + expectedText + "}");
        } catch (Exception e) {
           Exceptions.handle(getClass(), e);
        }
        return this;
    }

    public Verify attributeToBe(@NotNull final String attribute, @NotNull final boolean expected) {
        try {
            softAssert.assertEquals(attribute, expected);
            Log4JLogger.logINFO(Verify.class, new Object() {}.getClass().getEnclosingMethod().getName(), "Attribute {" + attribute + "} is equals to the expected {" + expected + "}");
        } catch (Exception e) {
           Exceptions.handle(getClass(), e);
        }
        return this;
    }

    public Verify attributeToBe(@NotNull final String attribute, @NotNull final String expected) {
        try {
            softAssert.assertEquals(attribute, expected);
            Log4JLogger.logINFO(Verify.class, new Object() {}.getClass().getEnclosingMethod().getName(), "Attribute {" + attribute + "} is equals to the expected {" + expected + "}");
        } catch (Exception e) {
           Exceptions.handle(getClass(), e);
        }
        return this;
    }



    public Verify equals(@NotNull final Object actual, @NotNull final Object expected) {
        try {
            softAssert.assertEquals(actual, expected);
            Log4JLogger.logINFO(Verify.class, new Object() {}.getClass().getEnclosingMethod().getName(), "Actual {" + actual + "} is equals to the expected {" + expected + "}");
        } catch (Exception e) {
            Exceptions.handle(getClass(), e);
        }
        return this;
    }

    public Verify notEquals(@NotNull final Object actual, @NotNull final Object expected) {
        try {
            softAssert.assertNotEquals(actual, expected);
            Log4JLogger.logINFO(Verify.class, new Object() {}.getClass().getEnclosingMethod().getName(), "Actual {" + actual + "} not equals to the expected {" + expected + "}");
        } catch (Exception e) {
            Exceptions.handle(getClass(), e);
        }
        return this;
    }

    public Verify assertTrue(@NotNull final boolean condition) {
        try {
            softAssert.assertTrue(condition);
            Log4JLogger.logINFO(Verify.class, new Object() {}.getClass().getEnclosingMethod().getName(), "Actual condition is: {" + condition + "}");
        } catch (Exception e) {
            Exceptions.handle(getClass(), e);
        }
        return this;
    }

    public Verify assertFalse(@NotNull final boolean condition) {
        try {
            softAssert.assertFalse(condition);
            Log4JLogger.logINFO(Verify.class, new Object() {}.getClass().getEnclosingMethod().getName(), "Actual condition is: {" + condition + "}");
        } catch (Exception e) {
            Exceptions.handle(getClass(), e);
        }
        return this;
    }
}