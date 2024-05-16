Feature: Add to Cart

  Scenario: Add and Remove Products from Cart
    Given user is on landing page
    When user logs in with username "standard_user" and password "secret_sauce"
    And user adds product "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    Then user sees two products in the cart indicator

    When user removes the products from the cart
    Then user sees no products in the cart indicator