package pages.web.old;

import org.openqa.selenium.By;
import org.testng.Assert;
import web.utilities.actions.AlertActions;
import web.utilities.actions.ElementActions;

public class SignUp {
    private static final By username_TextBox = By.id("sign-username");
    private static final By password_TextBox = By.id("sign-password");
    private static final By signUp_Button = By.xpath("/descendant::div[@class=\"modal-footer\"]//button[contains(text(), \"Sign up\")]");

    public static void signUp(String username, String password) {
        HomePage.openSignUpTap();
        ElementActions.type(username_TextBox, username);
        ElementActions.type(password_TextBox, password);
        ElementActions.click(signUp_Button);
    }

    public static void validateSignUpSuccessfulMessageInConfirmationAlert(String expectedText) {
        Assert.assertEquals(AlertActions.getAlertText(), expectedText);
    }

    public static void acceptSignUpConfirmAlert() {
        AlertActions.acceptAlert();
    }
}