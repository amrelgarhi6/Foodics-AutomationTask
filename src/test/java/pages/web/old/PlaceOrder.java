package pages.web.old;

import org.openqa.selenium.By;
import org.testng.Assert;
import web.utilities.actions.ElementActions;

public class PlaceOrder {
    private static final By name_TextBox = By.id("name");
    private static final By country_TextBox = By.id("country");
    private static final By city_TextBox = By.id("city");
    private static final By card_TextBox = By.id("card");
    private static final By month_TextBox = By.id("month");
    private static final By year_TextBox = By.id("year");
    private static final By purchase_Button = By.xpath("/descendant::div[@class=\"modal-footer\"]//button[contains(text(), \"Purchase\")]");
    private static final By successfulPurchase_HeaderText = By.xpath("/descendant::div[@class=\"sweet-alert  showSweetAlert visible\"]//h2");
    private static final By successfulPurchase_ConfirmButton = By.xpath("/descendant::div[@class=\"sa-confirm-button-container\"]//button");

    public static void typePurchaseData(String name, String country, String city, String card, String month, String year) {
        ElementActions.type(name_TextBox, name);
        ElementActions.type(country_TextBox, country);
        ElementActions.type(city_TextBox, city);
        ElementActions.type(card_TextBox, card);
        ElementActions.type(month_TextBox, month);
        ElementActions.type(year_TextBox, year);
    }

    public static void completePurchase() {
        ElementActions.click(purchase_Button);
    }

    public static void validateSuccessfulPurchase(String expectedText) {
        Assert.assertEquals(ElementActions.getText(successfulPurchase_HeaderText), expectedText);
    }

    public static void acceptSuccessfulPurchaseMessage() {
        ElementActions.click(successfulPurchase_ConfirmButton);
    }
}
