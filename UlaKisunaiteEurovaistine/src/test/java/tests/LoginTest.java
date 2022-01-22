package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;


public class LoginTest extends BaseTest {

    @Test (priority = 0)
    public void acceptAllCookiesLoginAndRegister() {
        BasePage basePage = new BasePage(driver);
        basePage.clickAcceptCookies();
        basePage.clickLogin();
    }

    @Test(priority = 1)
    public void loginAndRegistrationFormAndErrorMessageIsDisplayed() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.checkIfLoginFormIsDisplayed();
        loginPage.checkIfRegistrationFormIsDisplayed();
        loginPage.registrationFormIsFillUp();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)");

        loginPage.pressRegistrationButton();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");

        loginPage.checkIfCorrectErrorMessageIsShown();
    }
}
