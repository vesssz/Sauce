package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Commons {
    public static WebDriver driver;

    public Commons(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for specific input locator and then types given string
     *
     * @param text    The text to be typed
     * @param locator The input locator to wait for and type text in
     */
    public static void type(String text, By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(10));
        find(locator).sendKeys(text);
    }

    /**
     * Waits for specific input locator and then clicks
     *
     * @param locator The locator to wait for and click
     */
    public static void click(By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(10));
        find(locator).click();
    }

    /**
     * Waits for specific condition number of seconds
     *
     * @param condition The text to be typed
     * @param timeOut   The time in Duration.ofSeconds
     */
    protected static void waitFor(ExpectedCondition<WebElement> condition, Duration timeOut) {
        timeOut = timeOut != null ? timeOut : Duration.ofSeconds(3);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(condition);
    }

    /**
     * Waits for visibility of locator
     *
     * @param locator The element to wait for
     * @param timeOut The time for waiting
     */
    public static void waitForVisibilityOf(By locator, Duration... timeOut) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOut.length > 0 ? timeOut[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    //Find element using given locator
    public static WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public static String getTestURL() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//global.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        String url = properties.getProperty("testURL");
        return url;
    }

    public static boolean isElementDisplayed(By... locators) {
        try {
            for (By locator : locators) {
                WebElement element = driver.findElement(locator);
                if (!element.isDisplayed()) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

