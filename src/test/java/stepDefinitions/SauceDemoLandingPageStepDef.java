package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.InventoryPage;
import pageObjects.SauceDemoLandingPage;
import utils.TestSetUp;

import java.io.IOException;

public class SauceDemoLandingPageStepDef {

    TestSetUp testSetUp;
    SauceDemoLandingPage sauceDemoLandingPage;
    InventoryPage inventoryPage;

    public SauceDemoLandingPageStepDef(TestSetUp testSetUp) {
        this.testSetUp = testSetUp;
        this.sauceDemoLandingPage = testSetUp.pageFactory.getSauceDemoLandingPage();
        this.inventoryPage = testSetUp.pageFactory.getInventoryPage();

    }

    @Given("user is on landing page")
    public void user_is_on_landing_page() {
        System.out.println("Opening landing page");
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_username_and_password_password(String username, String password) throws IOException {
        System.out.println("Logging in with username and password");
        sauceDemoLandingPage.enterCredentials(username, password);
        Assert.assertTrue(inventoryPage.isUserLocatedOnProductsPage("inventory.html"));
    }
    @Then("user logs out")
    public void user_logs_out() throws IOException {
        sauceDemoLandingPage.logUserOut();
        Assert.assertTrue(sauceDemoLandingPage.isUserLoggedOut());
    }
}
