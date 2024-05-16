package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.CartPage;
import utils.TestSetUp;

public class CartPageStepDef {

    TestSetUp testSetUp;
    CartPage cartPage;


    public CartPageStepDef(TestSetUp testSetUp) {
        this.testSetUp = testSetUp;
        this.cartPage = testSetUp.pageFactory.getCartPage();
    }

    @When("purchased products {string} and {string} are added in cart")
    public void purchased_products_and_are_added_in_cart(String product, String product1){
        Assert.assertTrue(cartPage.isProductInCart(product) && cartPage.isProductInCart(product1));
    }

    @When("user tries to submits empty form")
    public void user_tries_to_submits_empty_form() {
        cartPage.clickCheckout();
        cartPage.submitPurchaseForm();
    }

    @Then("purchase is not completed and errors are displayed")
    public void purchase_is_not_completed_and_errors_are_displayed() {
        Assert.assertTrue(cartPage.purchaseNotPlaced());
    }

    @When("user fills in form with first name {string}, last name {string} and post code {string}")
    public void user_fills_in_form_with_first_name_last_name_and_post_code(String firstName, String lastName, String postCode) {
        cartPage.clickCheckout();
        cartPage.fillInPurchaseForm(firstName, lastName, postCode);
        cartPage.submitPurchaseForm();
    }

    @Then("purchased products {string} and {string} are displayed upon checkout")
    public void purchased_products_and_are_displayed_upon_checkout(String product, String product1) {
        Assert.assertTrue(cartPage.isProductInCart(product));
        Assert.assertTrue(cartPage.isProductInCart(product1));
        Assert.assertTrue(cartPage.isTotalPriceCorrect());
        Assert.assertTrue(cartPage.areOrderButtonsDisplayed());
        cartPage.clickFinishOrder();
    }

    @Then("the order is submitted")
    public void the_order_is_submitted() {
        Assert.assertTrue(cartPage.isOrderSent());
    }

}
