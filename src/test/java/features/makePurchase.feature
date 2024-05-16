Feature: Make Purchase

  Scenario: Attempt to Make a Purchase With Empty Form
    Given user is on landing page
    And user logs in with username "standard_user" and password "secret_sauce"
    And user adds product "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    Then user sees two products in the cart indicator
    When user opens Cart
    And purchased products "Sauce Labs Backpack" and "Sauce Labs Bike Light" are added in cart
    And user tries to submits empty form
    Then purchase is not completed and errors are displayed
    And user logs out

  Scenario: Submit a Purchase
    Given user is on landing page
    And user logs in with username "standard_user" and password "secret_sauce"
    And user adds product "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    Then user sees two products in the cart indicator
    When user opens Cart
    And purchased products "Sauce Labs Backpack" and "Sauce Labs Bike Light" are added in cart
    And user fills in form with first name "SomeRandom", last name "Shopaholic" and post code "9999"
    Then purchased products "Sauce Labs Backpack" and "Sauce Labs Bike Light" are displayed upon checkout
    And the order is submitted
    And user logs out
