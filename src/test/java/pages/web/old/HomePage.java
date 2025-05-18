package pages.web.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import web.utilities.actions.ElementActions;

import java.util.List;
import java.util.Random;

public class HomePage {

    private static final By page_HeaderTitle = By.id("nava");
    private static final By cartTap = By.linkText("Cart");
    private static final By logInTap = By.linkText("Log in");
    private static final By signUpTap = By.linkText("Sign up");
    private static final By category_Phones = By.xpath("/descendant::div[@class=\"list-group\"]//a[contains(text(), \"Phones\")]");
    private static final By category_Laptops = By.xpath("/descendant::div[@class=\"list-group\"]//a[contains(text(), \"Laptops\")]");
    private static final By category_Monitors = By.xpath("/descendant::div[@class=\"list-group\"]//a[contains(text(), \"Monitors\")]");
    private static final By card_block = By.className("card-block");
    private static final By card_title = By.className("card-title");

    public static void openHomeTap() {
        ElementActions.click(page_HeaderTitle);
    }

    public static void openCartTap() {
        ElementActions.click(cartTap);
    }

    public static void openLogInTap() {
        ElementActions.click(logInTap);
    }

    public static void openSignUpTap() {
        ElementActions.click(signUpTap);
    }

    public static void openPhonesCategory() {
        ElementActions.click(category_Phones);
    }

    public static void openLaptopsCategory() {
        ElementActions.click(category_Laptops);
    }

    public static void openMonitorsCategory() {
        ElementActions.click(category_Monitors);
    }

    public static List<WebElement> getListOfCards() {
        return ElementActions.findElements(card_block);
    }

    public static int getNoOfCards() {
        return getListOfCards().size();
    }

    public static void validateCategoryHasItems() {
        Assert.assertFalse(getListOfCards().isEmpty());
    }

    public static void validateHomePageHeaderText(String expectedText) {
        Assert.assertEquals(ElementActions.getText(page_HeaderTitle), expectedText);
    }

    public static List<WebElement> getListOfCardTitles() {
        return ElementActions.findElements(card_title);
    }

    public static void selectRandomPhone() {
        Random random = new Random();
        getListOfCardTitles().get(random.nextInt(0, getNoOfCards() - 1)).click();
    }
}