@Regression @Regression_FE
Feature: Purchase affordable video games from Amazon Egypt

  As a registered user
  I want to browse and purchase video games under a budget
  So that I can buy affordable games with preferred shipping and payment options

  Background:
    Then  Validate that user lands on amazon website

  Scenario: Add all video games under 15,000 EGP with filters and verify cart summary
    When User navigates to the "Video Games" section from the main menu
    Then Validate that user lands on "Video Games" section
    And User applies filters [Free Shipping] and [Condition: "New"]
    Then Validate that selected filters are applied and checked
    And User sorts the product list by "Price: High to Low"
    Then Validate that sorting product filter applied with "Price: High to Low"
    And User adds all products priced below 15000 EGP to my shopping cart
    When User navigates to shopping Cart
    Then Validate that user lands on "Shopping Cart"
    And  Validate that all selected products reflected in my cart