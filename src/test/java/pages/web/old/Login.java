package pages.web.old;

import org.openqa.selenium.By;
import web.utilities.actions.ElementActions;

public class Login {
    private static final By username_TextBox = By.id("loginusername");
    private static final By password_TextBox = By.id("loginpassword");
    private static final By login_Button = By.xpath("/descendant::div[@class=\"modal-footer\"]//button[contains(text(), \"Log in\")]");

    public static void login(String username, String password) {
        HomePage.openLogInTap();
        ElementActions.type(username_TextBox, username);
        ElementActions.type(password_TextBox, password);
        ElementActions.click(login_Button);
    }
}