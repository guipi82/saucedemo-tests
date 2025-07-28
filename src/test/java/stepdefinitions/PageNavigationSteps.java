package stepdefinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import pages.ProductsPage;
import utils.Hooks;

public class PageNavigationSteps {

    WebDriver driver;
    ProductsPage productsPage;

    public PageNavigationSteps() {
        this.driver = Hooks.getDriver();           // zentrale Initialisierung
        this.productsPage = new ProductsPage(driver);
    }

    @When("der Benutzer wählt \"About\" aus")
    public void userNavigatesToProductsPage() {
        productsPage.clickOnAbout();
    }

    @Then("wird die About-Seite geöffnet")
    public void userSeesProductsDisplayed() {
        boolean isAboutPageDisplayed = productsPage.assertAboutPageVisible();
        assertEquals("Die About-Seite wurde nicht angezeigt.", true, isAboutPageDisplayed);
    }

    @When("der Benutzer klickt auf \"Add to cart\" im Warenkorb")
    public void userAddsProductToCart() {
        productsPage.addToCart();
    }

    @And("der Benutzer klickt auf das Burger-Menü im Warenkorb")
    public void userClicksOnBurgerMenuInCart() {
        productsPage.clickOnBurgerMenu();
    }

    @When("der Benutzer wählt \"Reset App State\" aus")
    public void userClicksOnResetAppState() {
        productsPage.clickOnResetAppState();
    }
}
