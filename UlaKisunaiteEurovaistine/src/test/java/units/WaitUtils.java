package units;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class WaitUtils {

    /**
     * Wait until element is visible
     * @param driver WebDriver
     * @param timeout int
     */
    public static void waitForElementToBeVisible(WebDriver driver, By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait until element is shown
     * @param driver Webdriver
     * @param timeout int
     */
    public static void waitForElementToShowUp(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait until text to be
     * @param driver Webdriver
     * @param expectedProductsInCart String
     * @param timeout int
     */
    public static void waitForTextToBe(WebDriver driver, By locator, String expectedProductsInCart, int timeout){
        WebDriverWait waiting = new WebDriverWait(driver,Duration.ofSeconds(timeout));
        waiting.until(ExpectedConditions.textToBe(locator, expectedProductsInCart));
    }

}
