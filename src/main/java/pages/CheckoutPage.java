
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locator-Definition
    private By checkoutButton = By.id("checkout");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By logo = By.cssSelector("div.app_logo");
    private By BackHomeButton = By.id("back-to-products");
    private By descriptionLabel = By.cssSelector("[data-test='cart-desc-label']");
    private By paymentInfoLabel = By.cssSelector("[data-test='payment-info-label']");
    private By shippingInfoLabel = By.cssSelector("[data-test='shipping-info-label']");
    private By pricetotalLabel = By.cssSelector("[data-test='total-info-label']");

    private By checkoutCompleteMessage = By.className("complete-header");
    private By productsTitle = By.cssSelector("[data-test='title']");


    // Method to click the checkout button
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
        // Wait for the finish button to be clickable after continuing
        WebElement continuebutton = driver.findElement(this.continueButton);
        if (!continuebutton.isDisplayed()) {
            throw new NoSuchElementException("Finish button is not displayed after clicking continue.");
        }    
        continuebutton.click();        
    }

    public void clickFinish() {
        // Wait for the finish button to be visible before clicking
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
        // Wait for the finish button to be clickable
        WebElement finishbutton = driver.findElement(this.finishButton);
        if (!finishbutton.isDisplayed()) {
            throw new NoSuchElementException("Finish button is not displayed.");
        }
        finishbutton.click();   
    }

    public String getCheckoutCompleteMessage() {
        return driver.findElement(checkoutCompleteMessage).getText();
    }

    public String getDescriptionLabel() {
        return driver.findElement(descriptionLabel).getText();
    }

    public String getPaymentInfoLabel() {
        return driver.findElement(paymentInfoLabel).getText();
    }

    public String getShippingInfoLabel() {
        return driver.findElement(shippingInfoLabel).getText();
    }

    public String getTotalLabel() {
        return driver.findElement(pricetotalLabel).getText();
    }

    public void clickBackToProducts() {
        driver.findElement(BackHomeButton).click();
    }

    public String getProductsTitle() {
        return driver.findElement(productsTitle).getText();
    }   
}
