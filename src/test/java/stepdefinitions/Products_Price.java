package stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.CartPage;
import pages.ProductsPage;
import utils.DriverFactory;
import utils.Hooks;

public class Products_Price {
    
    // This class will contain step definitions related to product price verification
    // such as checking the price of products, comparing prices, etc.

    WebDriver driver = Hooks.getDriver(); ; // Get the WebDriver instance from DriverFactory
    ProductsPage productsPage = new ProductsPage(driver); // Create an instance of ProductsPage
    CartPage cartPage = new CartPage(driver); // Create an instance of CartPage
    
    @Given("der Benutzer sieht das Produkt {string}")
    public void userSeesProduct(String productName) {
        // Warte, bis Produktseite sichtbar ist – z. B. Titel "Products"
        new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        // Verify that the product is displayed
        boolean isProductDisplayed = productsPage.isProductDisplayed(productName);
        if (!isProductDisplayed) {
            throw new AssertionError("Das Produkt '" + productName + "' wurde nicht angezeigt.");
        }
    } 
    
    //method to verify product price and Product name
    @Then("wird der {string} für das {string} angezeigt")
    public void verifyProductPrice(String expectedPrice, String productName) {
        // Verify that the product price is displayed correctly     
        String actualPrice = productsPage.getProductPriceByName(productName);
        assertEquals("Der Preis des Produkts stimmt nicht überein", expectedPrice, actualPrice);
    }    
    
    @And("wird der {string} für das {string} im Warenkorb angezeigt")
    public void verifyPriceAndProductName(String expectedPrice, String productName) {
        // Verify that the product name is displayed correctly
        String actualName = cartPage.getProductName(productName);
       // Verify that the product price is displayed correctly     
        String actualPrice = cartPage.getProductPrice(productName);
        assertEquals("Der Preis des Produkts stimmt nicht überein", expectedPrice, actualPrice);
        assertEquals("Der Name des Produkts stimmt nicht überein", productName, actualName);
    }
}
