package stepdefinitions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProductsPage;
import utils.Hooks;

public class LogoutSteps {
    // This class will contain step definitions related to user logout actions   
    WebDriver driver;
    ProductsPage productsPage;  // Create an instance of ProductsPage
    LoginPage loginPage; 
     
    @Given("der Benutzer klickt auf das Burger-Menü")
    public void clickOnBurgerMenu() {
        driver = Hooks.getDriver(); 
        productsPage = new ProductsPage(driver);
        productsPage.clickOnBurgerMenu(); // Click the burger menu to open the side menu
    }
    
    @When("der Benutzer wählt \"Logout\" aus")
    public void selectLogout() {
        // Click the logout option from the burger menu
        productsPage.clickOnLogout(); // Click the logout option
    }
    
    @Then("wird die Login-Seite angezeigt")
    public void verifyLoginPageDisplayed() {
        loginPage = new LoginPage(driver);
        boolean loginPageVisible = loginPage.assertLoginPageVisible();
        // Verify that the login page is displayed      
         assertEquals("Die Login-Seite wurde nicht angezeigt nach dem Logout.", true, loginPageVisible);
    }    
}
