Feature: Add to Cart

  Scenario Outline: Add and Remove Products from Cart
    Given user is on landing page
    When user logs in with username "<username>" and password "<password>"
    And user adds product "<product1>" and "<product2>" to the cart
    Then user sees two products in the cart indicator

    When user removes the products from the cart
    Then user sees no products in the cart indicator
    And user logs out

    Examples:
      | username      | password     | product1                | product2                          |
      | standard_user | secret_sauce | Sauce Labs Backpack     | Sauce Labs Bike Light             |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Sauce Labs Onesie                 |
      | standard_user | secret_sauce | Sauce Labs Onesie       | Test.allTheThings() T-Shirt (Red) |
