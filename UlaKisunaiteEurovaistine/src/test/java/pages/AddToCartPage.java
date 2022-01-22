package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static units.WaitUtils.waitForElementToShowUp;
import static units.WaitUtils.waitForTextToBe;

public class AddToCartPage {

   public WebDriver driver;

   public AddToCartPage(WebDriver driver) {
        this.driver = driver;
    }


   public By pageTitle = By.tagName("h1");
   public By productBrand = By.cssSelector(".filter-hdr");

   public By productBrandTitle = By.className("filter-hdr");
   public By productName = By.cssSelector(".filter-text-search .form-control");

   public By checkboxVisible = By.xpath("//input[@value='GRIPEX']");
   public By productCardTitle = By.className("product-card--title");

   public By productCard = By.className("product-card");
   public By cardWidgetAmount = By.className("cart-widget--amount");

   public By buttons = By.tagName("button");
   public By correctProduct = By.className("product-name");

   public WebElement getProductName() { return driver.findElement(productName); }
   public WebElement getCheckboxVisible() { return driver.findElement(checkboxVisible); }

   public WebElement getPageTitle() { return driver.findElement(pageTitle); }
   public WebElement getCartWidgetAmount() { return driver.findElement(cardWidgetAmount); }

   /**
    * Checks page title
    */
   public void checkIfPageTitleWasCorrect() {
      String expectedResults = "Paieškos rezultatai ieškant \"Nereceptiniai vaistai\"";
      String pageName = getPageTitle().getText();
      assertEquals(pageName, expectedResults, "Page title was not correct");
   }

   /**
    * Find product brand title
    * @return element
    */
   public List<WebElement> productBrandTitle() {
      return driver.findElements(productBrandTitle);
   }

   /**
    * Enter product brand ir product category search
    */
   public void enterProductBrandInProductCategorySearch() {
      waitForElementToShowUp(driver, productBrand, 5);
      List<WebElement> element = productBrandTitle();
      String tradeMark = "Prekės ženklas:";
      WebElement productBrand = element.stream()
              .filter(zodis -> zodis.getText().contains(tradeMark)).findAny().get();
      productBrand.click();

      String searchKey = "GRIPEX";
      getProductName().sendKeys(searchKey);
   }

   /**
    * Find product card title
    * @return products
    */
   public List<WebElement> getProductCardTitle() {
      return driver.findElements(productCardTitle);
   }

   /**
    * Selects on checkbox
    */
   public void selectOnCheckbox() {
      waitForElementToShowUp(driver, checkboxVisible, 2);
      getCheckboxVisible().click();

      List<WebElement> products = getProductCardTitle();
      for (WebElement product : products) {
         Assert.assertTrue(product.getText().toLowerCase().contains("gripex"));
      }
   }

   /**
    * Find product cards
    * @return amount
    */
   public List<WebElement> getProductCards() {
      return driver.findElements(productCard);
   }

   /**
    * Puts two products in the cart
    * @param productName String
    */
   public void putTwoProductsInTheCart(String productName) {
      List<WebElement> productsCards = getProductCards();
      WebElement amount = getCartWidgetAmount();
      String cartAmountBefore = amount.getText();
      for (WebElement productCard : productsCards) {
         if (productCard.findElement(productCardTitle).getText().equals(productName)){
            productCard.findElement(buttons).click();
            String expectedProductsInCart = String.valueOf((Integer.parseInt(cartAmountBefore)+1));
            waitForTextToBe(driver, cardWidgetAmount, expectedProductsInCart, 2);
            return;
         }
      }
      Assert.fail("Product does not exist");
   }

   /**
    * Find correct product
    * @return product
    */
   public List<WebElement> getProducts() {
      return driver.findElements(correctProduct);
   }

   /**
    * Checks if correct products put in the cart
    * @param productName String
    */
   public void checkIfCorrectProductPutInTheCart(String productName) {
      List<WebElement> products = getProducts();
      for (WebElement product : products) {
         if (product.getText().equals(productName)) {
            return;
         }
      }
      Assert.fail("Product was not put in the cart");
   }
}
