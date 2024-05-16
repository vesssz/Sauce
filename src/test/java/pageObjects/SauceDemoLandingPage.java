package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Commons;
import utils.TestBase;

import java.io.IOException;

public class SauceDemoLandingPage {
    public WebDriver driver;

    public SauceDemoLandingPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By USERNAME_INPUT = By.xpath("//input[@placeholder = 'Username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@placeholder = 'Password']");
    private static final By SUBMIT_BTN = By.xpath("//input[@type= 'submit']");
    private static final By BURGER_MENU = By.xpath("//div[@class = 'bm-burger-button']");
    private static final By LOGOUT_BTN = By.xpath("//a[@id='logout_sidebar_link']");

    /**
     * Enters credentials for specific user in the login form
     * @param username
     * @param password
     */
    public void enterCredentials(String username, String password) {
        System.out.println("enterCredentials - called");
        Commons.type(username, USERNAME_INPUT);
        Commons.type(password, PASSWORD_INPUT);
        Commons.click(SUBMIT_BTN);
        System.out.println("enterCredentials - done");
    }
    public void logUserOut(){
        Commons.click(BURGER_MENU);
        Commons.click(LOGOUT_BTN);
    }
    public boolean isUserLoggedOut() throws IOException {
        String baseUrl = Commons.getTestURL();
        String currentURL =driver.getCurrentUrl();
        boolean isLoggedOutURL = currentURL.equalsIgnoreCase(baseUrl);
        boolean isLoginFormDisplayed = Commons.isElementDisplayed(USERNAME_INPUT, PASSWORD_INPUT, SUBMIT_BTN);
        return isLoggedOutURL && isLoginFormDisplayed;

    }
}
