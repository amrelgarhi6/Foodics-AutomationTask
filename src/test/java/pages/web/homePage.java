package pages.web;

import org.openqa.selenium.By;
import utiltites.assertions.Assertions;
import web.utilities.actions.ElementActions;

import static web.utilities.actions.ElementActions.scrollToElement;

public class homePage {



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static By allBurgerMenuCTA() {return By.xpath("//a[@data-csa-c-slot-id= 'HamburgerMenuDesktop']");}
    private static By seeAllShopByCategoryCTA() {return By.cssSelector("a.hmenu-compressed-btn");}
    private static By chooseCategory(String shopByCategoryName) {return By.xpath("//a[text()='" + shopByCategoryName + "']");}
    private static By chooseGamesCategoryCTA(String gamesCategoryName) {return By.xpath("//div[text()='" + gamesCategoryName + "']");}
    private static By amazonHomePageContent() {return By.id("pageContent");}




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Methods  ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void clickOnAllBtnFromBurgerMenu() {
        ElementActions.click(allBurgerMenuCTA());
    }

    public static void clickSeeAllShopCategoryCTA(){
        ElementActions.click(seeAllShopByCategoryCTA());
    }
    public static void chooseShopCategory(String shopByCategoryName){
        ElementActions.forceClick(chooseCategory(shopByCategoryName));
    }
    public static void chooseGamesCategory(String gamesCategoryName){
        scrollToElement(chooseGamesCategoryCTA(gamesCategoryName));
        ElementActions.click((chooseGamesCategoryCTA(gamesCategoryName)));
    }

    public static void assertLandingOnAmazonHomePage()
    {
        Assertions.hardAssert().elementDisplayed(amazonHomePageContent());
    }




}
