package utiltites.assertions;

public class Assertions {
    public Assertions() {
    }

    public static Assert hardAssert() {
        return new Assert();
    }

    public static Verify softAssert() {
        return new Verify();
    }
}