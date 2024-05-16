package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.InventoryPage;
import utils.TestSetUp;

public class InventoryPageStepDef {
    private String product;
    private String product1;

    TestSetUp testSetUp;
    InventoryPage inventoryPage;
    CartPage cartPage;

    public InventoryPageStepDef(TestSetUp testSetUp) {
        this.testSetUp = testSetUp;
        this.inventoryPage = testSetUp.pageFactory.getInventoryPage();
        this.cartPage = testSetUp.pageFactory.getCartPage();

    }

    @When("user adds product {string} and {string} to the cart")
    public void user_adds_product_product_and_product2_to_the_cart(String product, String product1){
        System.out.println("Adding items to cart");
        this.product = product;
        this.product1 = product1;
        inventoryPage.addItemToCart(product);
        inventoryPage.addItemToCart(product1);
    }

    @Then("user sees two products in the cart indicator")
    public void user_sees_two_products_in_the_cart_indicator() {
        System.out.println("Validating if items in cart");
        Assert.assertTrue(inventoryPage.areProductsPurchased(product, product1));
    }

    @When("user removes the products from the cart")
    public void user_removes_the_products_from_the_cart() {
        System.out.println("Removing items from cart");
        inventoryPage.removeItemFromCart(product);
        inventoryPage.removeItemFromCart(product1);
    }

    @Then("user sees no products in the cart indicator")
    public void user_sees_no_products_in_the_cart_indicator() {
        System.out.println("Verifying no items in cart");
        Assert.assertFalse(inventoryPage.isAnyItemPurchased());
    }
    @When("user opens Cart")
    public void user_opens_cart(){
        inventoryPage.openCart();
    }
}
