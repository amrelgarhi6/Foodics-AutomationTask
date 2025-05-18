package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utiltites.assertions.Assertions;
import utiltites.logger.Log4JLogger;
import web.utilities.actions.ElementActions;
import web.utilities.driver_manager.DriverManager;

import java.util.*;

public class categoryResultPage {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final static By productContainer = By.cssSelector("div.s-main-slot > div[data-component-type='s-search-result']");
    private final static By productTitle = By.cssSelector("a h2 span");
    private final static By productPriceWhole = By.xpath("//*[@aria-describedby='price-link']//span[@class='a-price-whole']");
    private final static By addToCartButton = By.xpath(".//input[@aria-labelledby='add-to-cart-button-announce'] | .//input[@id='add-to-cart-button']");
    private final static By productSummaryWanningPopUp = By.id("attach-warranty-display");
    private final static By noThanksBtn = By.xpath("//*[@aria-labelledby='attachSiNoCoverage-announce']");
    private final static By productAddedSuccessMsg = By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS");
    private final static By nextPage = By.cssSelector("a.s-pagination-next");
    private static By conditionOptions(String conditionFilterType) {return By.xpath("(//*[contains(@id,'condition-type')]//span[text()='" + conditionFilterType + "'])");}
    private final static By videoGamesTitle = By.cssSelector(".pageBanner");
    private final static By selectFilterOptionCheckBox = By.xpath("//*[@aria-labelledby='Free Shipping']");
    private final static By freeShippingCheckBoxCheckedStatus = By.xpath("//*[contains(@aria-label,'Remove the filter Free Shipping')]");
    private final static By conditionFilterCheckedStatus = By.xpath("(//*[contains(@id,'condition-type')])[1]//*[@aria-current='true']");
    private final static By selectSortingResult = By.xpath("//select[contains(@id,'result-sort-select')]");
    private final static By sortProductPromptStatus = By.className("a-dropdown-prompt");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Methods  ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void enableFreeShippingFilter() {
        ElementActions.forceClick(selectFilterOptionCheckBox);
    }

    public static void selectConditionFilter(String conditionFilterType) {
        ElementActions.scrollToElement(conditionOptions(conditionFilterType));
        ElementActions.forceClick(conditionOptions(conditionFilterType));
    }

    public static void selectSortingResultType(String sortResultType) {
        ElementActions.selectDropdownByVisibleText(selectSortingResult, sortResultType);
    }


    public static List<Map<String, String>> addItemToBasket(int minPrice, int maxPrice) {
        List<Map<String, String>> addedProducts = new ArrayList<>();
        Set<String> visitedTitles = new HashSet<>();
        boolean continueProcessing = true;

        do {
            System.out.println("Start processing page");

            List<WebElement> products = Objects.requireNonNull(DriverManager.getDriver()).findElements(productContainer);

            for (WebElement product : products) {
                try {

                    Log4JLogger.logINFO(categoryResultPage.class,"Checking product");
                    String title = product.findElement(productTitle).getText();

                    // Skip if already visited
                    if (visitedTitles.contains(title)) {
                        Log4JLogger.logINFO(categoryResultPage.class,"Already processed product: " + title);
                        continue;
                    }

                    String priceText = product.findElement(productPriceWhole).getText();
                    int price = parsePrice(priceText);
                    Log4JLogger.logINFO(categoryResultPage.class,"Price Value > " + price);
                    if (price < minPrice) {
                        Log4JLogger.logINFO(categoryResultPage.class,"Price below minimum target. Stopping.");
                        continueProcessing = false;
                        break;
                    }

                    if (price >= minPrice && price <= maxPrice) {
                        Thread.sleep(3000);
                        Log4JLogger.logINFO(categoryResultPage.class,"Price within range, visiting product page: " + title);
                        visitedTitles.add(title);
                        product.findElement(productTitle).click();
                        Thread.sleep(3000);

                        WebElement detailCartButton = DriverManager.getDriver().findElement(addToCartButton);
                        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", detailCartButton);
                        Thread.sleep(2000);

                        try {
                            WebElement warningPopup = DriverManager.getDriver().findElement(productSummaryWanningPopUp);
                            if (warningPopup.isDisplayed()) {
                                ElementActions.click(noThanksBtn);
                                Thread.sleep(4000);
                                Assertions.hardAssert().elementDisplayed(productAddedSuccessMsg);
                            }
                        } catch (NoSuchElementException ignored) {
                        }

                        // Move product info collection inside the price condition block
                        Map<String, String> productInfo = new HashMap<>();
                        productInfo.put("title", title);
                        productInfo.put("price", String.valueOf(price));
                        addedProducts.add(productInfo);

                        DriverManager.getDriver().navigate().back();
                        DriverManager.getDriver().navigate().back();
                        Thread.sleep(3000);
                    }

                } catch (Exception e) {
                    Log4JLogger.logINFO(categoryResultPage.class,"Skipping product due to exception: " + e.getMessage());
                }
            }

            if (!continueProcessing) {
                break;
            }

            Log4JLogger.logINFO(categoryResultPage.class,"Products added so far:");
            for (Map<String, String> item : addedProducts) {
                System.out.println(item);
            }

            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            try {
                WebElement next = DriverManager.getDriver().findElement(nextPage);
                if (next.isDisplayed()) {
                    next.click();
                } else {
                    break;
                }
            } catch (NoSuchElementException e) {
                break;
            }

        } while (true);

        System.out.println("Final list of added products:");
        for (Map<String, String> item : addedProducts) {
            System.out.println(item);
        }
        return addedProducts;
    }


    public static int parsePrice(String priceText) {
        return Integer.parseInt(priceText.replaceAll("[^\\d]", ""));
    }


    public static void assertLandingOnCategoryResultPage(String expectedPageHeader) {
        Assertions.hardAssert().elementDisplayed(videoGamesTitle);
        Assertions.hardAssert().textToBe(ElementActions.getText(videoGamesTitle), expectedPageHeader);
    }

    public static void assertFreeShippingCheckFilterApplied() {
        Assertions.hardAssert().elementDisplayed(freeShippingCheckBoxCheckedStatus);
    }

    public static void assertConditionFilterApplied() {
        ElementActions.scrollToElement(conditionOptions("New"));
        Assertions.hardAssert().elementDisplayed(conditionFilterCheckedStatus);
    }

    public static void assertSortProductsPromptApplied(String expectedSortPrompt) {
        Assertions.hardAssert().textToBe(ElementActions.getText(sortProductPromptStatus), expectedSortPrompt);
    }


}









