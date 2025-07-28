package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverFactory;
import utils.Hooks;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

public class Products_CartSteps {
   // This class will contain step definitions related to product actions
   // such as adding products to the cart, removing them, etc.

   WebDriver driver; // Get the WebDriver instance from DriverFactory
   LoginPage loginPage; // Create an instance of LoginPage
   ProductsPage productsPage; // Create an instance of ProductsPage
   CartPage cartPage; // Create an instance of CartPage

   @Given("der Benutzer wählt das {string} aus")
   public void iAmOnTheProductsPage(String product) {
      driver = Hooks.getDriver();
      productsPage = new ProductsPage(driver); 
      // click on the product Sauce Labs Backpack
      new WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
      productsPage.clickOnProductByName(product);

   }

   @Given("der Benutzer wählt die folgenden Produkte aus:")
   public void iSelectProducts(io.cucumber.datatable.DataTable dataTable) {
      // Convert the DataTable to a list of product names
      new WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
      List<String> productNames = dataTable.asList(String.class);
      if (productNames == null || productNames.isEmpty()) {
         throw new IllegalArgumentException("Keine Produkte angegeben.");
      }
      // Iterate through the list of products and add them to the cart
      for (String product : productNames) {
         productsPage.addProductToCart(product);
      }

   }

   @When("der Benutzer klickt auf \"Add to cart\"")
   public void iAddAProductToTheCart() {
      // click on add to cart
      productsPage.addToCart();
   }

   @When("der Benutzer klickt auf den Warenkorb")
   public void iGoToTheCart() {
      // click on the cart icon
      productsPage.goToCart();
   }

   @Then("befindet sich das {string} im Warenkorb")
   public void theProductShouldBeInTheCart(String productName) {
      cartPage = new CartPage(driver);
      String actualproductname = cartPage.getProductName(productName);

      if (actualproductname != null) {
         assertEquals(productName, actualproductname);
      }
   }

   @Then("enthält der Warenkorb folgende Produkte:")
   public void theCartShouldContainProducts(io.cucumber.datatable.DataTable dataTable) {
      List<String> expectedProducts = dataTable.asList();

      for (String product : expectedProducts) {
         String actualProduct = cartPage.getProductName(product);
         assertEquals("Produkt sollte im Warenkorb sein: " + product, product, actualProduct);
      }
   }

   @When("der Benutzer entfernt das {string} aus Warenkorb")
   public void removeFromCart(String productName) {
      cartPage.removeFromCart(productName);
   }

   @When("der Benutzer entfernt die Produkte aus Warenkorb")
   public void removeProductsFromCart(io.cucumber.datatable.DataTable dataTable) {
      List<String> productsToRemove = dataTable.asList();

      for (String product : productsToRemove) {
         cartPage.removeFromCart(product);
      }
   }

   @Then("ist der Warenkorb leer")
   public void emptyCart() {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(d -> {
         String count = cartPage.productnberInCart();
         return count == null || count.equals("0") || count.isBlank();
      });

      String noProductFound = cartPage.productnberInCart();
      assertTrue(noProductFound == null || noProductFound.equals("0") || noProductFound.isBlank());
   }

}
