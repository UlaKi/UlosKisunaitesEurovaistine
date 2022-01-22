package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.BasePage;


public class AddToCartTest extends BaseTest {

    @DataProvider(name = "search")
    public static Object[][] searchData(){
        return new Object[][] {
                {"Gripex, plėvele dengtos tabletės, N24"},
                {"GripexThermal 300/20/5 mg, milteliai geriamajam tirpalui, N12"}
                // Neišeina įdėti ("Gripex, plėvele dengtos tabletės, N12"), nes Eurovaistinės tinklapyje uždėti ribojimai.
     };
    }

    @Test(priority = 0)
    public void acceptCookiesThenEnterSearchText() {
        BasePage basePage = new BasePage(driver);
        basePage.clickAcceptCookies();
        basePage.enterSearchText();

        AddToCartPage addToCartPage = new AddToCartPage(driver);
        addToCartPage.checkIfPageTitleWasCorrect();
        addToCartPage.enterProductBrandInProductCategorySearch();
        addToCartPage.selectOnCheckbox();
    }

    @Test(priority = 1, dataProvider = "search")
    public void putItemInTheCart(String productName) {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        addToCartPage.putTwoProductsInTheCart(productName);
    }

    @Test(priority = 2)
    public void pressOnTheCart() {
        BasePage basePage = new BasePage(driver);
        basePage.pressOnTheCart();
    }

    @Test(priority = 3, dataProvider = "search")
    public void checkIfCorrectProductPutInTheCart(String productName) {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        addToCartPage.checkIfCorrectProductPutInTheCart(productName);
    }
}

