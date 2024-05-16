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
    public void purchased_products_and_are_added_in_cart(String product, String product1) {
        Assert.assertTrue(cartPage.isProductInCart(product) && cartPage.isProductInCart(product1));
    }

    @When("user tries to submits empty form")
    public void user_tries_to_submits_empty_form() {
        cartPage.clickCheckout();
        cartPage.submitAddressForm();
    }

    @Then("purchase is not completed and errors are displayed")
    public void purchase_is_not_completed_and_errors_are_displayed() {
        Assert.assertTrue(cartPage.purchaseNotPlaced());
    }

}
