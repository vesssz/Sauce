Feature: Make Purchase

  Scenario Outline: Attempt to Make a Purchase With Empty Form
    Given user is on landing page
    And user logs in with username "<username>" and password "<password>"
    And user adds product "<product1>" and "<product2>" to the cart
    Then user sees two products in the cart indicator
    When user opens Cart
    And purchased products "<product1>" and "<product2>" are added in cart
    And user tries to submits empty form
    Then purchase is not completed and errors are displayed
    And user logs out
    Examples:
      | username      | password     | product1                 | product2                          |
      | standard_user | secret_sauce | Sauce Labs Backpack      | Sauce Labs Bike Light             |
      | standard_user | secret_sauce | Sauce Labs Fleece Jacket | Sauce Labs Bolt T-Shirt           |
      | standard_user | secret_sauce | Sauce Labs Onesie        | Test.allTheThings() T-Shirt (Red) |


  Scenario Outline: Submit a Purchase
    Given user is on landing page
    And user logs in with username "<username>" and password "<password>"
    And user adds product "<product1>" and "<product2>" to the cart
    Then user sees two products in the cart indicator
    When user opens Cart
    And purchased products "<product1>" and "<product2>" are added in cart
    And user fills in form with first name "SomeRandom", last name "Shopaholic" and post code "9999"
    Then purchased products "<product1>" and "<product2>" are displayed upon checkout
    And the order is submitted
    And user logs out
    Examples:
      | username      | password     | product1                | product2                          |
      | standard_user | secret_sauce | Sauce Labs Backpack     | Sauce Labs Bike Light             |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Sauce Labs Fleece Jacket          |
      | standard_user | secret_sauce | Sauce Labs Onesie       | Test.allTheThings() T-Shirt (Red) |