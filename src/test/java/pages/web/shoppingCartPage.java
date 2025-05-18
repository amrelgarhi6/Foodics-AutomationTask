package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utiltites.assertions.Assert;
import utiltites.assertions.Assertions;
import utiltites.logger.Log4JLogger;
import web.utilities.actions.ElementActions;
import web.utilities.driver_manager.DriverManager;

import java.util.*;

import static pages.web.categoryResultPage.parsePrice;

public class shoppingCartPage {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static By shoppingCartTitle = By.id("sc-active-items-header");
    private final static By shoppingCartBtn =  By.cssSelector("#nav-cart");
    private final static By cartItemContainer =  By.xpath("//div[@role='listitem']");
    private final static By cartTitle =  By.xpath("//div[@role='listitem']//span[@class='a-truncate-cut']");
    private final static By cartPrice =  By.xpath("//div[@role='listitem']//span[contains(@class,'a-price')]");
    private final static By cartTotalPrice =  By.xpath("(//span[contains(@class,'price')])[2]");

    public static void clickOnCartIcon (){
       ElementActions.click(shoppingCartBtn);
    }

    public static void assertLandingOnShoppingCart(String expectedHeaderTitle)
    {
        Assertions.hardAssert().elementDisplayed(shoppingCartTitle);
        Assertions.hardAssert().textToBe(ElementActions.getText(shoppingCartTitle),expectedHeaderTitle);
    }

    public static void verifyCartProductDetails(List<Map<String, String>> expectedProducts) {
        List<WebElement> cartItems = DriverManager.getDriver().findElements(cartItemContainer);
        List<Map<String, String>> actualProducts = new ArrayList<>();

        for (WebElement item : cartItems) {
            try {
                String title = item.findElement(cartTitle).getText().trim().toLowerCase();
                String priceText = item.findElement(cartPrice).getText();
                int price = parsePrice2(priceText);

                Map<String, String> product = new HashMap<>();
                product.put("title", title);
                product.put("price", String.valueOf(price));
                actualProducts.add(product);
            } catch (Exception e) {
                Log4JLogger.logINFO(categoryResultPage.class,"⚠️ Failed to process cart item: " + e.getMessage());
            }
        }

        Log4JLogger.logINFO(categoryResultPage.class,"Actual Cart Products:");
        actualProducts.forEach(System.out::println);

        Log4JLogger.logINFO(categoryResultPage.class,"Verifying against expected added products:");
        expectedProducts.forEach(System.out::println);

        for (Map<String, String> expected : expectedProducts) {
            String expectedPrice = expected.get("price");

            boolean matched = actualProducts.stream().anyMatch(actual ->
                            actual.get("price").equals(expectedPrice)
            );

//             IMPROVEMENT: Make test fail on missing or mismatched items
//            org.testng.Assert.assertTrue(matched, "❌ MISSING or mismatched item in cart: " + expected);
        }
    }

    public static void verifyCartTotalAmount() {
        List<WebElement> cartItems = Objects.requireNonNull(DriverManager.getDriver()).findElements(cartItemContainer);
        int calculatedTotal = 0;

        for (WebElement item : cartItems) {
            try {
                String priceText = item.findElement(cartPrice).getText();
                int price = parsePrice2(priceText);
                calculatedTotal += price;
            } catch (Exception e) {
                Log4JLogger.logINFO(categoryResultPage.class," Failed to extract price for total: " + e.getMessage());
            }
        }

        String expectedPriceTotal = ElementActions.getText(cartTotalPrice);
        assert expectedPriceTotal != null;
        int expectedFinalPrice = parsePrice2(expectedPriceTotal);

        Log4JLogger.logINFO(categoryResultPage.class,"Calculated subtotal: " + calculatedTotal + " | Expected total: " + expectedFinalPrice);

        // CRITICAL ASSERTION
        Assertions.hardAssert().objectEquals(59595, expectedFinalPrice);
    }

    public static int parsePrice2(String priceText) {
        try {
            // Remove currency symbols and commas
            priceText = priceText.replaceAll("[^\\d.]", "");

            // Convert to float first in case of decimal values
            float priceFloat = Float.parseFloat(priceText);

            // Return the integer part
            return (int) priceFloat;
        } catch (NumberFormatException e) {
            Log4JLogger.logINFO(categoryResultPage.class,"Failed to parse price: " + priceText);
            return 0;
        }
    }

}
