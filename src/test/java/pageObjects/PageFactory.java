package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    public WebDriver driver;
    public SauceDemoLandingPage sauceDemoLandingPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public SauceDemoLandingPage getSauceDemoLandingPage() {
        sauceDemoLandingPage = new SauceDemoLandingPage(driver);
        return sauceDemoLandingPage;
    }

    public InventoryPage getInventoryPage() {
        inventoryPage = new InventoryPage(driver);
        return inventoryPage;
    }

    public CartPage getCartPage() {
        cartPage = new CartPage(driver);
        return cartPage;
    }

}
