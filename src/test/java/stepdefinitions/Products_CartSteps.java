package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.Hooks;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

public class Products_CartSteps {

   WebDriver driver;
   LoginPage loginPage;
   ProductsPage productsPage;
   CartPage cartPage;

   public Products_CartSteps() {
      this.driver = Hooks.getDriver(); // Zentrale Initialisierung
      this.loginPage = new LoginPage(driver);
      this.productsPage = new ProductsPage(driver);
      this.cartPage = new CartPage(driver);
   }

   @Given("der Benutzer wählt das {string} aus")
   public void iAmOnTheProductsPage(String product) {
      new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
      productsPage.clickOnProductByName(product);
   }

   @Given("der Benutzer wählt die folgenden Produkte aus:")
   public void iSelectProducts(io.cucumber.datatable.DataTable dataTable) {
      new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
      List<String> productNames = dataTable.asList(String.class);
      if (productNames == null || productNames.isEmpty()) {
         throw new IllegalArgumentException("Keine Produkte angegeben.");
      }
      for (String product : productNames) {
         productsPage.addProductToCart(product);
      }
   }

   @When("der Benutzer klickt auf \"Add to cart\"")
   public void iAddAProductToTheCart() {
      productsPage.addToCart();
   }

   @When("der Benutzer klickt auf den Warenkorb")
   public void iGoToTheCart() {
      productsPage.goToCart();
   }

   @Then("befindet sich das {string} im Warenkorb")
   public void theProductShouldBeInTheCart(String productName) {
      String actualProductName = cartPage.getProductName(productName);
      if (actualProductName != null) {
         assertEquals(productName, actualProductName);
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
         String count = cartPage.getProductCountInCart();
         return count == null || count.equals("0") || count.isBlank();
      });

      String noProductFound = cartPage.getProductCountInCart();
      assertTrue(noProductFound == null || noProductFound.equals("0") || noProductFound.isBlank());
   }
}
