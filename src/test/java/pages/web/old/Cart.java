package pages.web.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import web.utilities.actions.ElementActions;
import web.utilities.driver_manager.DriverManager;
import web.utilities.waits.Waits;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Cart {
    private static final By deleteProduct_button = By.xpath("//a[contains(text(), \"Delete\")]");
    private static final By placeOrder_Button = By.xpath("//Button[contains(text(), \"Place Order\")]\n");
    private static final By productTable_Rows = By.xpath("//tr[@class=\"success\"]");

    public static List<WebElement> getListOfDeleteElements() {
        return ElementActions.findElements(deleteProduct_button);
    }

    public static int getNoOfDeleteElements() {
        return getListOfDeleteElements().size();
    }

    public static void deleteRandomItem() {
        Random random = new Random();
        getListOfDeleteElements().get(random.nextInt(0, getNoOfDeleteElements())).click();
    }

    public static void placeOrder() {
        ElementActions.click(placeOrder_Button);
    }

    public static void validateCartIsEmpty() {
        Waits.waitForElementToBeInVisible(productTable_Rows);
        Assert.assertTrue(Objects.requireNonNull(DriverManager.getDriver()).findElements(productTable_Rows).isEmpty());
    }

    public static void validateCartIsNotEmpty() {
        Assert.assertTrue(ElementActions.findElements(productTable_Rows).size() >= 1);
    }
}
