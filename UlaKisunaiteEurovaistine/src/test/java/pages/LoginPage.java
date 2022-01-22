package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;
import static units.WaitUtils.waitForElementToShowUp;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public By registration = By.id("customer_registration");
    public By registrationButton = By.cssSelector("#customer_registration button");

    public By visibleLogin = By.className("default-form");

    public By registrationEmail = By.id("customer_registration_email");
    public By password = By.id("customer_registration_user_plainPassword_first");
    public By repeatPassword = By.id("customer_registration_user_plainPassword_second");

    public By generalOffers = By.id("customer_registration_marketing_generalOffers");
    public By personalOffers = By.id("customer_registration_marketing_personalOffers");

    public By addressInput = By.id("customer_registration_marketing_typeAddress");
    public By registrationForm = By.xpath("//div[starts-with(@class, 'datatable')]");
    public By errorMessage = By.xpath("//*[text()[contains(.,'Norėdami tęsti, privalote sutikti su taisyklėmis ir privatumo politika.')]]");


    public WebElement getLogin() { return driver.findElement(visibleLogin); }
    public WebElement getRegistration() { return driver.findElement(registration); }
    public WebElement getRegistrationButton() { return driver.findElement(registrationButton); }

    public WebElement getEmail(){ return driver.findElement(registrationEmail); }
    public WebElement getPassword(){ return  driver.findElement(password); }
    public WebElement repeatPassword(){ return  driver.findElement(repeatPassword); }

    public WebElement getGeneralOffers(){ return  driver.findElement(generalOffers); }
    public WebElement getPersonalOffers(){ return  driver.findElement(personalOffers); }
    public WebElement getAddressInput(){ return  driver.findElement(addressInput); }

    /**
     * Checks Login form visibility and if it is displayed
     */
    public void checkIfLoginFormIsDisplayed() {
        boolean loginVisible = getLogin().isDisplayed();
        assertTrue(loginVisible, "Something is wrong");
    }

    /**
     * Checks Registration form visibility and if it is displayed
     */
    public void checkIfRegistrationFormIsDisplayed() {
        boolean registrationFormVisible = getRegistration().isDisplayed();
        assertTrue(registrationFormVisible, "Registration form not visible");
    }

    /**
     * Registration form is filled up in all required fields and all required checkboxes been checked
     */
    public void registrationFormIsFillUp() {
        waitForElementToShowUp(driver, registrationForm, 5);
        String text = "testas1@gmail.com";
        String passText = "Testukas51";

        getEmail().sendKeys(text);
        getPassword().sendKeys(passText);
        repeatPassword().sendKeys(passText);
        getGeneralOffers().click();
        getPersonalOffers().click();
        getAddressInput().click();
    }

    /**
     * Clicks on registration button
     */
    public void pressRegistrationButton(){
        getRegistration();
        getRegistrationButton().click();
    }

    /**
     * Checks error message
     */
    public void checkIfCorrectErrorMessageIsShown() {
        waitForElementToShowUp(driver, errorMessage, 4);
        boolean errorMessageVisible = driver.findElement(errorMessage).isDisplayed();
        assertTrue(errorMessageVisible, "Error message was not correct");
    }
}
