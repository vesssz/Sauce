package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Commons;

import java.util.List;


public class CartPage {
    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    private static final Logger log = LoggerFactory.getLogger(CartPage.class);

    private static final By CHECKOUT_BTN = By.xpath("//button[@name='checkout']");
    private static final By CONTINUE_BUTTON = By.xpath("//input[@name='continue']");
    private static final String PRODUCT_XPATH_BASE = "//div[text()='placeholder']";
    private static final By FIRSTNAME_INPUT = By.xpath("//input[@placeholder='First Name']");
    private static final By LASTNAME_INPUT = By.xpath("//input[@placeholder='Last Name']");
    private static final By POSTCODE_INPUT = By.xpath("//input[@placeholder='Zip/Postal Code']");
    private static final By ERROR_BUTTON = By.xpath("//button[@class='error-button']");
    private static final By FINISH_ORDER_BTN = By.xpath("//button[@name='finish']");
    private static final By CANCEL_BUTTON = By.xpath("//button[@name='cancel']");
    private static final By COMPLETED_ORDER_CONFIRMATION = By.xpath("//h2[text()='Thank you for your order!']");
    private static final By INVENTORY_PRICE_LOCATOR = By.xpath("//div[@class = 'inventory_item_price']");
    private static final By TOTAL_PRICE = By.xpath("//div[@class = 'summary_subtotal_label']");

    /**
     * Clicks button Checkout on Cart Order page
     */
    public void clickCheckout() {
        log.info("clickCheckout - called");
        Commons.click(CHECKOUT_BTN);
        log.info("clickCheckout - done");
    }

    /**
     * Clicks button Finish on Cart Order page to complete order
     */
    public void clickFinishOrder() {
        log.info("clickFinishOrder - called");
        Commons.click(FINISH_ORDER_BTN);
        log.info("clickFinishOrder - done");
    }

    /**
     * Verifies if text for completed order is displayed
     *
     * @return boolean
     */
    public boolean isOrderSent() {
        log.info("isOrderSent - called...");
        return Commons.isElementDisplayed(COMPLETED_ORDER_CONFIRMATION);
    }

    /**
     * Fills in order details in Cart Order page
     *
     * @param firstName
     * @param lastName
     * @param postCode
     */
    public void fillInPurchaseForm(String firstName, String lastName, String postCode) {
        log.info("clickCheckout - called");
        Commons.type(firstName, FIRSTNAME_INPUT);
        Commons.type(lastName, LASTNAME_INPUT);
        Commons.type(postCode, POSTCODE_INPUT);
        log.info("clickCheckout - done");
    }

    /**
     * Submits the address form upon order
     */
    public void submitPurchaseForm() {
        log.info("submitAddressForm - called");
        Commons.click(CONTINUE_BUTTON);
        log.info("submitAddressForm - done");
    }

    /**
     * Verifies if an item is located inside Cart in the Cart Page.
     * Searches by string
     *
     * @param product
     * @return boolean
     */
    public boolean isProductInCart(String product) {
        log.info("isProductInCart - called for product ..." + product);
        String productXpath = getProductXPath(product);
        if (Commons.isElementDisplayed(By.xpath(productXpath))) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the total of prices of the items in cart corresponds to the total due without tax
     *
     * @return boolean
     */
    public boolean isTotalPriceCorrect() {
        log.info("isTotalPriceCorrect - called...");
        if (getTotalItemsPrice() == (getTotalDue())) {
            return true;
        }
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
        log.info("isProductInCart - called...");
        boolean isErrorButtonPresent = Commons.isElementDisplayed(ERROR_BUTTON);
        boolean isFormFilledIn = inputFieldsEmpty(FIRSTNAME_INPUT, LASTNAME_INPUT, POSTCODE_INPUT);
        return isErrorButtonPresent && isFormFilledIn;
    }
    /**
     * Verifies Finish order button and Cancel button are displayed
     *
     * @return boolean
     */
    public boolean areOrderButtonsDisplayed(){
        return Commons.isElementDisplayed(FINISH_ORDER_BTN) && Commons.isElementDisplayed(CANCEL_BUTTON);
    }

    /**
     * Verifies input field is empty for given locator
     *
     * @param locators
     * @return boolean
     */
    private boolean inputFieldsEmpty(By... locators) {
        log.info("inputFieldsEmpty - called...");
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

    /**
     * Gets the value of total due, mentioned at the bottom of page
     *
     * @return double
     */
    private double getTotalDue() {
        String priceText = driver.findElement(TOTAL_PRICE).getText();
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }

    /**
     * Checks separate item`s prices and returns the total amount of the order
     *
     * @return double
     */
    private double getTotalItemsPrice() {
        List<WebElement> elementList = driver.findElements(INVENTORY_PRICE_LOCATOR);
        double totalPrice = 0;
        for (WebElement element : elementList) {
            String elementText = element.getText();
            double value = Double.parseDouble(elementText.replaceAll("[^0-9.]", ""));
            totalPrice += value;
        }
        return totalPrice;
    }

}

