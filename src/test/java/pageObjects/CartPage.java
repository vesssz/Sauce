package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Commons;


public class CartPage {
    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By CHECKOUT_BTN = By.xpath("//button[@name='checkout']");
    private static final By CONTINUE_BUTTON = By.xpath("//input[@name='continue']");
    private static final String PRODUCT_XPATH_BASE = "//div[text()='placeholder']";
    private static final By FIRSTNAME_INPUT = By.xpath("//input[@placeholder='First Name']");
    private static final By LASTNAME_INPUT = By.xpath("//input[@placeholder='Last Name']");
    private static final By POSTCODE_INPUT = By.xpath("//input[@placeholder='Zip/Postal Code']");
    private static final By ERROR_BUTTON = By.xpath("//button[@class='error-button']");

    /**
     * Clicks button Checkout on Cart Order page
     */
    public void clickCheckout() {
        System.out.println("clickCheckout - called");
        Commons.click(CHECKOUT_BTN);
        System.out.println("clickCheckout - done");
    }

    /**
     * Submits the address form upon order
     */
    public void submitAddressForm() {
        System.out.println("submitAddressForm - called");
        //TODO Username, name, ZIP can be added if needed
        Commons.click(CONTINUE_BUTTON);
        System.out.println("submitAddressForm - done");
    }

    /**
     * Verifies if an item is located inside Cart in the Cart Page.
     * Searches by string
     *
     * @param product
     * @return boolean
     */
    public boolean isProductInCart(String product) {
        System.out.println("isProductInCart - called...");
        String productXpath = getProductXPath(product);
        if (driver.findElement(By.xpath(productXpath)).isDisplayed()) {
            return true;
        }
        ;
        return false;
    }

    /**
     * Returns String with the xpath for a product, depending on the provided product name
     *
     * @param productName
     * @return String
     */
    private String getProductXPath(String productName) {
        return PRODUCT_XPATH_BASE.replace("placeholder", productName);
    }

    /**
     * Verifies Purchase fields are not filled in and Error button is displayed
     *
     * @return boolean
     */
    public boolean purchaseNotPlaced() {
        System.out.println("isProductInCart - called...");
        boolean isErrorButtonPresent = Commons.isElementDisplayed(ERROR_BUTTON);
        boolean isFormFilledIn = inputFieldsEmpty(FIRSTNAME_INPUT, LASTNAME_INPUT, POSTCODE_INPUT);
        return isErrorButtonPresent && isFormFilledIn;
    }

    /**
     * Verifies input field is empty for given locator
     *
     * @param locators
     * @return boolean
     */
    private boolean inputFieldsEmpty(By... locators) {
        System.out.println("inputFieldsEmpty - called...");
        boolean allEmpty = true;
        for (By locator : locators) {
            String value = Commons.find(locator).getAttribute("value");
            if (!value.isEmpty()) {
                allEmpty = false;
                break;
            }
        }
        return allEmpty;
    }

}

