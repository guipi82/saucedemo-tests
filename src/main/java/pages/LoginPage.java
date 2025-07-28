package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
    private WebDriver driver;


    //This constructor initializes the WebDriver instance for the LoginPage
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    
    // Locator-Definition
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button"); 
    private By errorMesage = By.cssSelector("h3[data-test='error']");

    //Action on the LoginPage
    public void openLoginPage() {
        // Navigate to the login page of the application
        driver.get("https://www.saucedemo.com/");
    }

    public void enterUsername(String username) {
        // Find the username input field and enter the username
        driver.findElement(usernameField).sendKeys(username);
    }       

    public void enterPassword(String password) {
        // Find the password input field and enter the password
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        // Find the login button and click it to submit the form
        driver.findElement(loginButton).click();
    }

    public void loginAs(String username, String password) {
        // Perform the login action by entering credentials and clicking the login button
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        // Get the error message displayed on the login page
        return driver.findElement(errorMesage).getText();
    }

    public boolean isErrorMessageDisplayed() {
        // Check if the error message is displayed
        return driver.findElement(errorMesage).isDisplayed();
    }

    public boolean assertLoginPageVisible() {
        // Check if the login page is visible by verifying the presence of the username field
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            return driver.findElement(usernameField).isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // If the username field is not found, the login page is not visible
        }
    }

}
