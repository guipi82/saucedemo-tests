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
 * CartPage ist eine Klasse zur Repräsentation der Warenkorbseite.
 * Sie bietet Methoden zur Interaktion mit Elementen auf der Warenkorbseite.
 */
public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator für das Warenkorb-Badge (Produktzähler)
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    /**
     * Öffnet direkt die Warenkorbseite
     */
    public void openCartPage() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    /**
     * Entfernt ein Produkt aus dem Warenkorb basierend auf dem Produktnamen
     */
    public void removeFromCart(String productName) {
        String normalizedName = productName.toLowerCase().replace(" ", "-");
        String idValue = "remove-" + normalizedName;
        By removeButtonBy = By.id(idValue);

        try {
            WebElement removeButton = driver.findElement(removeButtonBy);
            if (removeButton.isDisplayed()) {
                removeButton.click();
                System.out.println("Produkt entfernt: " + productName);
            } else {
                System.out.println("Remove-Button nicht sichtbar für: " + productName);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Kein Remove-Button gefunden für Produkt: " + productName);
        }
    }

    /**
     * Gibt den Namen eines Produkts im Warenkorb zurück, wenn es sichtbar ist
     */
    public String getProductName(String productName) {
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

    /**
     * Gibt die Anzahl der Produkte im Badge zurück oder null, wenn kein Badge sichtbar ist
     */
    public String getProductCountInCart() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) {
            System.out.println("Kein Warenkorb-Badge sichtbar");
            return null;
        }
        String badgeText = badges.get(0).getText();
        System.out.println("Badge sichtbar mit Anzahl: " + badgeText);
        return badgeText;
    }

    /**
     * Gibt den Preis eines bestimmten Produkts im Warenkorb zurück
     */
    public String getProductPrice(String productName) {
        By priceLocator = By.xpath("//div[@data-test='inventory-item-name' and normalize-space(text())='" + productName
                + "']/../../..//div[@data-test='inventory-item-price']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator));

        return driver.findElement(priceLocator).getText(); // Beispiel: "$29.99"
    }
}
