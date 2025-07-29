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
import utils.Hooks;

public class Products_Price {

    // This class will contain step definitions related to product price verification
    WebDriver driver;
    ProductsPage productsPage;
    CartPage cartPage;

    @Given("der Benutzer sieht das Produkt {string}")
    public void userSeesProduct(String productName) {
        driver = Hooks.getDriver();
        productsPage = new ProductsPage(driver); 
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        boolean isProductDisplayed = productsPage.isProductDisplayed(productName);
        if (!isProductDisplayed) {
            throw new AssertionError("Das Produkt '" + productName + "' wurde nicht angezeigt.");
        }
    } 
    
    @Then("wird der {string} für das {string} angezeigt")
    public void verifyProductPrice(String expectedPrice, String productName) {
        driver = Hooks.getDriver(); // Sicherstellen, dass driver gesetzt ist
        productsPage = new ProductsPage(driver); 
        String actualPrice = productsPage.getProductPrice(productName);
        assertEquals("Der Preis des Produkts stimmt nicht überein", expectedPrice, actualPrice);
    }    
    
    @And("wird der {string} für das {string} im Warenkorb angezeigt")
    public void verifyPriceAndProductName(String expectedPrice, String productName) {
        driver = Hooks.getDriver(); // HINZUGEFÜGT: driver sicher initialisieren
        cartPage = new CartPage(driver); // CartPage korrekt mit gültigem Driver initialisieren
        String actualName = cartPage.getProductName(productName);
        String actualPrice = cartPage.getProductPrice(productName);
        assertEquals("Der Preis des Produkts stimmt nicht überein", expectedPrice, actualPrice);
        assertEquals("Der Name des Produkts stimmt nicht überein", productName, actualName);
    }
}
