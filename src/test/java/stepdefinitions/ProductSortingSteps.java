package stepdefinitions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProductsPage;
import utils.Hooks;

public class ProductSortingSteps {
    
    // This class will contain step definitions related to product sorting actions
    // Currently, no step definitions are provided in the context
    // You can add methods here to handle product sorting steps as needed
    WebDriver driver; // Get the WebDriver instance from DriverFactory
    ProductsPage productsPage; // Create an instance of ProductsPage


    @When("der Benutzer sortiert die Produkte nach {string}")
    public void userSortsProducts(String sortOption) {
        driver = Hooks.getDriver();
        productsPage = new ProductsPage(driver);
        productsPage.sortProducts(sortOption);
    }

    @Then("sind die Produktnamen alphabetisch aufsteigend sortiert")
    public void verifyProductsSortedAlphabetically() {
        boolean isSorted = productsPage.isProductNameSortedAscending();
        // Assert that the product names are sorted alphabetically in ascending order
        assertEquals("Die Produktnamen sind nicht alphabetisch aufsteigend sortiert.", true, isSorted);

    }

    @Then("sind die Produktnamen alphabetisch absteigend sortiert")
    public void verifyProductsSortedInDescendingOrder() {
        boolean isSorted = productsPage.isProductNameSortedDescending();
        // Assert that the product names are sorted alphabetically in descending order
        assertEquals("Die Produktnamen sind nicht alphabetisch absteigend sortiert.", true, isSorted);
    }

    @Then("sind die Produktpreise aufsteigend sortiert")
    public void verifyProductPricesSortedAscending() {
        boolean isSorted = productsPage.isProductPriceSortedAscending();
        // Assert that the product prices are sorted in ascending order
        assertEquals("Die Produktpreise sind nicht aufsteigend sortiert.", true, isSorted);
    }

    @Then("sind die Produktpreise absteigend sortiert")
    public void verifyProductPricesSortedDescending() {
        boolean isSorted = productsPage.isProductPriceSortedDescending();
        // Assert that the product prices are sorted in descending order
        assertEquals("Die Produktpreise sind nicht absteigend sortiert.", true, isSorted);
    }



}
