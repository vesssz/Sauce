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
    private static final By POSTALCODE_INPUT = By.xpath("//input[@placeholder='Zip/Postal Code']");
    private static final By ERROR_BUTTON = By.xpath("//button[@class='error-button' and contains(., 'Error: First Name is required')]");

    public void clickCheckout(){
        Commons.click(CHECKOUT_BTN);
    }
    public void submitAddressForm(){
        //TODO Username, name, ZIP can be added if needed
        Commons.click(CONTINUE_BUTTON);
    }
    public boolean isProductInCart(String product){
        String productXpath = getProductXPath(product);
        System.out.println("HERE " + productXpath);
         if (driver.findElement(By.xpath(productXpath)).isDisplayed()){
             return true;
         };
         return false;
    }
    private String getProductXPath(String productName) {
        return PRODUCT_XPATH_BASE.replace("placeholder", productName);

    }

}

