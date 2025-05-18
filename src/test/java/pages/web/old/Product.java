package pages.web.old;

import org.openqa.selenium.By;
import org.testng.Assert;
import web.utilities.actions.AlertActions;
import web.utilities.actions.ElementActions;

public class Product {
    private static final By addToCart_Button = By.xpath("//a[contains(text(), \"Add to cart\")]");

    public static void validateUserNavigatedToProductsPage() {
        Assert.assertTrue(ElementActions.findElement(addToCart_Button).isDisplayed());
    }

    public static void addToCart() {
        ElementActions.click(addToCart_Button);
    }

    public static void validateProductAddedMessageInConfirmationAlert(String expectedText) {
        Assert.assertTrue(AlertActions.getAlertText().contains(expectedText));
    }

    public static void acceptProductAddedConfirmAlert() {
        AlertActions.acceptAlert();
    }
}