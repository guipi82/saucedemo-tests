package stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.ProductsPage;
import utils.Hooks;

public class PageNavigationSteps {
    
    WebDriver driver = Hooks.getDriver(); // Get the WebDriver instance from Hooks
    ProductsPage productsPage = new ProductsPage(driver); // Create an instance of ProductsPage
    CartPage cartPage = new CartPage(driver); // Create an instance of CartPage
    

    @When("der Benutzer wählt \"About\" aus")
    public void userNavigatesToProductsPage() {
        productsPage.clickOnAbout();
    }

    @Then("wird die About-Seite geöffnet")
    public void userSeesProductsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space(text())='Sign in']")));
        // Verify that the About page is displayed
        boolean isAboutPageDisplayed = productsPage.assertAboutPageVisible();    
        // Assert that the About page is displayed
        assertEquals("Die About-Seite wurde nicht angezeigt.", true, isAboutPageDisplayed);
    } 


    @When("der Benutzer klickt auf \"Add to cart\" im Warenkorb")
    public void userAddsProductToCart() {
        // Click on the "Add to Cart" button in the cart
        productsPage.addToCart();
    }

    @And("der Benutzer klickt auf das Burger-Menü im Warenkorb")
    public void userClicksOnBurgerMenuInCart() {
        // Click on the burger menu icon in the cart
        productsPage.clickOnBurgerMenu();
    }   

    @When("der Benutzer wählt \"Reset App State\" aus")
    public void userClicksOnResetAppState() {
        // Click on the "Reset App State" link
        productsPage.clickOnResetAppState();
    }
}
