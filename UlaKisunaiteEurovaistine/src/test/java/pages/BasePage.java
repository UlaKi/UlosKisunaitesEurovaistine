package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static units.WaitUtils.waitForElementToBeVisible;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public By cookieBanner = By.id("onetrust-button-group");
    public By acceptCookies = By.id("onetrust-accept-btn-handler");
    public By login = By.id("user-block");
    public By searchInput = By.className("sn-suggest-input");
    public By cardWidget = By.id("cart-widget");

    public WebElement acceptCookies() { return driver.findElement(acceptCookies); }
    public WebElement findLogin() {return driver.findElement(login); }
    public WebElement getSearchInput() { return driver.findElement(searchInput); }
    public WebElement getCartWidget() { return driver.findElement(cardWidget); }

    /**
     * Clicks on accept cookies button after cookies banner become visible
     */
    public void clickAcceptCookies() {
        waitForElementToBeVisible(driver, cookieBanner, 5);
        acceptCookies().click();
    }

    /**
     * Clicks on Login
     */
    public void clickLogin(){
        findLogin().click();
    }

    public void enterSearchText() {
        WebElement element = getSearchInput();
        String searchText = "Nereceptiniai vaistai";
        element.sendKeys(searchText);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Clicks on Cart Icon
     */
    public void pressOnTheCart() {
        WebElement cart = getCartWidget();
        cart.click();
    }
}
