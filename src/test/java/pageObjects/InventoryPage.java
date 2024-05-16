package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Commons;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class InventoryPage {
    public WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    private static final Logger log = LoggerFactory.getLogger(InventoryPage.class);

    private static final String REMOVE_BTN_XPATH = "//button[text()='Remove']";
    private static final String ADD_TO_CART_BTN_XPATH = "//button[text()='Add to cart']";
    private static final String CART_ITEMS_BADGE_XPATH = "//span[@class ='shopping_cart_badge' ]";
    private static final By CART_ITEMS_BADGE_LOCATOR = By.xpath(CART_ITEMS_BADGE_XPATH);
    private static final By SHOPPING_CART_LOCATOR = By.xpath("//a[@class='shopping_cart_link']");

    /**
     * Searches for product in the page by String and adds it to cart
     *
     * @param productName
     */
    public void addItemToCart(String productName) {
        log.info("addItemToCart - called for item " + productName);
        WebElement productElem = findProductElementByName(productName);
        WebElement addToCartButton = productElem.findElement(By.xpath(ADD_TO_CART_BTN_XPATH));
        addToCartButton.click();
        log.info("addItemToCart - done");
    }

    /**
     * Searches for product in the page by String and removes it from cart
     *
     * @param productName
     */
    public void removeItemFromCart(String productName) {
        log.info("removeItemFromCart called for item " + productName);
        WebElement productElem = findProductElementByName(productName);
        WebElement removeFromCartButton = productElem.findElement(By.xpath(REMOVE_BTN_XPATH));
        removeFromCartButton.click();
        log.info("removeItemFromCart - done");
    }

    /**
     * Searches for products in the page by String and verifies
     * if Add to cart button is clicked and if cart locator count equal to purchased items
     *
     * @param products
     * @return boolean
     */
    public boolean areProductsPurchased(String... products) {
        log.info("areProductsPurchased - called");
        boolean result = true;
        for (String product : products) {
            if (!isProductPurchased(product)) {
                result = false;
                break;
            }
        }
        if (result && getCartItemsCount() != products.length) {
            result = false;
        }
        log.info("areProductsPurchased - done");
        return result;
    }

    /**
     * Searches for button REMOVE is present on page and verifies items in Cart are zero
     *
     * @return boolean
     */
    public boolean isAnyItemPurchased() {
        log.info("isAnyItemPurchased - called...");
        return !driver.findElements(By.xpath(REMOVE_BTN_XPATH)).isEmpty() || getCartItemsCount() != 0;
    }

    /**
     * Gets the current URL and checks if it is corresponding to Products page URL
     *
     * @param productsPageExtension
     * @return boolean
     */
    public boolean isUserLocatedOnProductsPage(String productsPageExtension) throws IOException {
        log.info("isUserLocatedOnProductsPage - called");
        String baseUrl = Commons.getTestURL();
        String expectedURL = baseUrl + productsPageExtension;
        String currentURL = driver.getCurrentUrl();
        log.info("isUserLocatedOnProductsPage - done");
        return expectedURL.equalsIgnoreCase(currentURL);
    }

    /**
     * Opens Cart Page
     */
    public void openCart() {
        log.info("openCart - called");
        Commons.click(SHOPPING_CART_LOCATOR);
        log.info("openCart - done");
    }

    /**
     * Searches for product in the page by String and verifies if Add to cart button is replaced with Remove button
     *
     * @param productName
     * @return boolean
     */
    private boolean isProductPurchased(String productName) {
        WebElement productEle = findProductElementByName(productName);
        if (productEle.findElement(By.xpath(REMOVE_BTN_XPATH)).isDisplayed()) {
            return true;
        }
        return false;
    }
    /**
     *  Returns the count of the purchased items shown on the Cart badge
     * @return int
     */
    private int getCartItemsCount() {
        if (driver.findElements(By.xpath(CART_ITEMS_BADGE_XPATH)).isEmpty()) {
            return 0;
        } else {
            WebElement cartBadgeElement = Commons.find(CART_ITEMS_BADGE_LOCATOR);
            String purchasedItemsCount = cartBadgeElement.getText();
            return Integer.parseInt(purchasedItemsCount);
        }
    }

    /**
     * Finds all product items in the page by String and returns WebElement if found.
     *
     * @param productName
     * @return WebElement or NoSuchElementException
     */
    private WebElement findProductElementByName(String productName) {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));

        for (WebElement item : items) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();
            if (itemName.equals(productName)) {
                return item;
            }
        }
        throw new NoSuchElementException("Item with name " + productName + " was not found on the page");
    }

}

