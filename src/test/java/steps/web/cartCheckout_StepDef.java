package steps.web;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static pages.web.categoryResultPage.*;
import static pages.web.homePage.*;
import static pages.web.shoppingCartPage.*;

public class cartCheckout_StepDef {

    List<Map<String, String>> expectedProducts;

    @When("User login with credentials")
    public void userLoginWithCredentials() {

    }

    @Then("Validate that user lands on amazon website")
    public void validateThatUserLandsOnAmazonWebsite() {
        assertLandingOnAmazonHomePage();
    }

    @When("User navigates to the {string} section from the main menu")
    public void userNavigatesToTheSectionFromTheMainMenu(String category) {
        clickOnAllBtnFromBurgerMenu();
        clickSeeAllShopCategoryCTA();
        chooseGamesCategory(category);
        chooseShopCategory("All Video Games");
    }

    @And("User sorts the product list by {string}")
    public void userSortsTheProductListBy(String sortResultOption) {
        selectSortingResultType(sortResultOption);
    }

    @And("User adds all products priced below {int} EGP to my shopping cart")
    public void userAddsAllProductsPricedBelowEGPToMyShoppingCart(int priceTarget) {
        expectedProducts = addItemToBasket(10000,priceTarget);

    }

    @And("Validate that all selected products reflected in my cart")
    public void validateThatAllSelectedProductsReflectedInMyCart() throws InterruptedException {
        verifyCartProductDetails(expectedProducts);
        verifyCartTotalAmount();
    }

    @Then("Validate that user lands on {string} section")
    public void validateThatUserLandsOnSection(String expectedPageHeaderText) {
        assertLandingOnCategoryResultPage(expectedPageHeaderText);
    }

    @Then("Validate that selected filters are applied and checked")
    public void validateThatSelectedFiltersAreAppliedAndChecked() {
        assertFreeShippingCheckFilterApplied();
        assertConditionFilterApplied();
    }

    @And("User applies filters [Free Shipping] and [Condition: {string}]")
    public void userAppliesFiltersFreeShippingAndCondition(String conditionFilterType) {
        enableFreeShippingFilter();
        selectConditionFilter(conditionFilterType);
    }

    @Then("Validate that sorting product filter applied with {string}")
    public void validateThatSortingProductFilterAppliedWith(String expectedSortPrompt) {
        assertSortProductsPromptApplied(expectedSortPrompt);
    }

    @When("User navigates to shopping Cart")
    public void userNavigatesToShoppingCart() {
        clickOnCartIcon();
    }

    @Then("Validate that user lands on {string}")
    public void validateThatUserLandsOn(String expectedHeaderTitle) {
        assertLandingOnShoppingCart(expectedHeaderTitle);
    }
}
