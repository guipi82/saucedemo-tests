package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;   // Import necessary WebDriver classes;   
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By; // Import By for locating elements
import pages.LoginPage;
import utils.DriverFactory;
import utils.Hooks;



public class LoginSteps {
    
    WebDriver driver; // Get the WebDriver instance from DriverFactory
    LoginPage loginPage; // Create an instance of LoginPage

    @Given("der Benutzer befindet sich auf der Login-Seite")
    public void userIsOnLoginPage() {
        driver = Hooks.getDriver();
        loginPage = new LoginPage(driver); // Initialize the LoginPage with the WebDriver instance
        loginPage.openLoginPage(); // Open the login page using the LoginPage class 
    }

    @When("der Benutzer gibt {string} und {string} ein")
    public void enterCredentials(String username, String password) {
         //Enter the username and password using the LoginPage methods
        loginPage.loginAs(username, password); // Use the login method from LoginPage
    }
    @Then("wird die Produkte-Seite angezeigt")
    public void verifyProductsPage() {
        driver = DriverFactory.getDriver();
        // Verify that the URL of the current page is the products page
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
            throw new AssertionError("Die Produkte-Seite wurde nicht angezeigt. Aktuelle URL: " + currentUrl);
        }
        
        // Optionally, you can also check for a specific element on the products page
        boolean isProductPageDisplayed = driver.findElement(By.className("inventory_list")).isDisplayed();
        if (!isProductPageDisplayed) {
            throw new AssertionError("Die Produkte-Seite wurde nicht korrekt angezeigt.");
        }        
    }
    @Then("wird das error_Message angezeigt")
    public void verifyErrorMessage() {
        boolean isErrordisplayed = loginPage.isErrorMessageDisplayed(); // Check if the error message is displayed
        String errorMessage = loginPage.getErrorMessage();
        // Verify that the error message is displayed on the login page
        if (!isErrordisplayed) {
            throw new AssertionError("Die Fehlermeldung wurde nicht angezeigt. Erwartete Fehlermeldung: 'Epic sadface: Username and password do not match any user in this service'. Aktuelle Fehlermeldung: " + errorMessage);
        }
        // Assert that the error message contains the expected text
        Assert.isTrue(errorMessage.contains("Epic sadface: Sorry, this user has been locked out."), 
            "Die Fehlermeldung ist nicht korrekt. Erwartete Fehlermeldung: 'Epic sadface: Sorry, this user has been locked out.'. Aktuelle Fehlermeldung: " + errorMessage);       
    }      
}
