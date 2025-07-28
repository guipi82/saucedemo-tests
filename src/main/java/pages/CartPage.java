package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * CartPage is a class that represents the cart page of the application.
 * It provides methods to interact with the cart page elements.
 */

public class CartPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver instance for CartPage
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator-Definition for cart page
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    // Method to open the cart page
    public void openCartPage() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    // Remove a product from the cart by its name
    public void removeFromCart(String productName) {
        String normalizedName = productName.toLowerCase().replace(" ", "-");
        String idValue = String.format("remove-%s", normalizedName); // korrektes ID-Attribut
        By removeButtonBy = By.id(idValue);

        try {
            WebElement removeButton = driver.findElement(removeButtonBy);
            if (removeButton.isDisplayed()) {
                removeButton.click();
                System.out.println("Produkt wurde entfernt: " + productName);
            } else {
                System.out.println("Remove-Button ist nicht sichtbar für: " + productName);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Kein Remove-Button gefunden für Produkt: " + productName);
        }
    }

    // get the name of a product in the cart
    public String getProductName(String productName) {
        // XPath für Produktname exakt wie angezeigt im UI
        String xpath = String.format("//div[@class='inventory_item_name' and normalize-space()='%s']", productName);
        By productNameBy = By.xpath(xpath);

        try {
            WebElement nameElement = driver.findElement(productNameBy);
            return nameElement.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Produktname nicht gefunden für: " + productName);
            return null;
        }
    }

    // Method to check if the product badge is visible and return its text
    public String productnberInCart() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.size() == 0) {
            System.out.println("No badge found");
            return null; // No badge found
        }
        String badgeText = badges.get(0).getText();
        System.out.println("Badge sichtbar: " + badgeText);
        // Return the text of the badge
        return badgeText;
    }

    public String getProductPrice(String productName) {
        By priceLocator = By.xpath("//div[@data-test='inventory-item-name' and normalize-space(text())='" + productName
                + "']/../../..//div[@data-test='inventory-item-price']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator));
        return driver.findElement(priceLocator).getText(); // z. B. "$29.99"
    }

}
