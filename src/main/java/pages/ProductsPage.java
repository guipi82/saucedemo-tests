package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class ProductsPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver instance for CartPage
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator-Definition for products page
    private By addToCartButton = By.id("add-to-cart");
    private By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    private By burgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By resetAppStateLink = By.id("reset_sidebar_link");
    private By signInButton = By.xpath("//button[normalize-space(text())='Sign in']");
    private By signUpButton = By.xpath("//button[normalize-space(text())='Sign up for free']");
    private By sortDropdown = By.cssSelector("select[data-test='product-sort-container']");
    private By productNames = By.xpath("//div[@data-test='inventory-item-name']");

    public void openProductsPage() {
        // Navigate to the products page of the application
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    // Method to add a product to the cart by its name
    public void addProductToCart(String productName) {
        // Produktname z. B. "Sauce Labs Backpack"
        String normalizedName = productName.toLowerCase().replace(" ", "-");
        String selector = String.format("[data-test='add-to-cart-%s']", normalizedName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        By addToCartButton = By.cssSelector(selector);
        driver.findElement(addToCartButton).click();
    }

    // Method to add a product to the cart
    public void addToCart() {
        // Click the "Add to Cart"
        driver.findElement(addToCartButton).click();
    }

    public void goToCart() {
        // Click the cart icon to navigate to the cart page
        driver.findElement(cartIcon).click();
    }

    // Method to click on a product by its name
    public void clickOnProductByName(String productName) {
        By productLocator = By.xpath("//div[normalize-space()='" + productName + "']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        driver.findElement(productLocator).click();
    }

    public String getProductPrice(String productName) {
        By priceLocator = By.xpath(
                "//div[//div[@class='inventory_item_name' and normalize-space(text())='\" + productName + \"']\"/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
        return driver.findElement(priceLocator).getText(); // z. B. "$29.99"
    }

    public String getProductPriceByName(String productName) {
        // XPath für Produktpreis
        By priceLocator = By.xpath("//div[@data-test='inventory-item-name' and normalize-space(text())='" + productName
                + "']/ancestor::div[@data-test='inventory-item-description']//div[@data-test='inventory-item-price']");

        return driver.findElement(priceLocator).getText();
    }

    public boolean isProductDisplayed(String productName) {
        try {
            By productLocator = By.xpath(
                    "//div[contains(@class, 'inventory_item_name') and normalize-space(text())='" + productName + "']");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
            return driver.findElement(productLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // Produkt nicht gefunden
        }
    }

    public void clickOnBurgerMenu() {
        // Wait for the burger menu to be visible before clicking
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu));
        // Click the burger menu to open the side menu
        driver.findElement(burgerMenu).click();
    }

    public void clickOnLogout() {
        // Wait for the burger menu to be visible before clicking
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        // Click the logout option from the burger menu
        driver.findElement(logoutLink).click();
    }

    public void clickOnAbout() {
        // Wait for the burger menu to be visible before clicking
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        // Click the "About" link from the burger menu
        driver.findElement(aboutLink).click();
    }

    public void clickOnResetAppState() {
        // Click on the "Reset App State" link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetAppStateLink));
        driver.findElement(resetAppStateLink).click();
    }

    public boolean assertAboutPageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Kontextwechsel falls neues Fenster
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (driver.getCurrentUrl().contains("saucelabs.com")) {
                return true;
            }
        }

        return false;

    }

    public void sortProducts(String sortOption) {
        // Select the sorting option from the dropdown
        Select dropdown = new Select(driver.findElement(sortDropdown));
        // Wait for the dropdown to be visible before selecting an option
        dropdown.selectByVisibleText(sortOption);
    }

    public boolean isProductNameSortedAscending() {
        List<String> names = getProductNames();
        return new ArrayList<>(names).equals(names.stream().sorted().toList());
    }

    public boolean isProductNameSortedDescending() {
        List<String> names = getProductNames();
        return new ArrayList<>(names).equals(names.stream().sorted(Comparator.reverseOrder()).toList());
    }

    public List<String> getProductNames() {
        List<WebElement> nameElements = driver.findElements(productNames);
        if (nameElements.isEmpty()) {
            throw new NoSuchElementException("Keine Produktnamen gefunden.");
        }
        List<String> names = new ArrayList<>();

        for (WebElement element : nameElements) {
            names.add(element.getText().trim());
        }

        return names;

    }

    public boolean isProductPriceSortedAscending() {
        List<Double> prices = getAllProductPrices();
        return new ArrayList<>(prices).equals(prices.stream().sorted().toList());
    }

    public boolean isProductPriceSortedDescending() {
        List<Double> prices = getAllProductPrices();
        return new ArrayList<>(prices).equals(prices.stream().sorted(Comparator.reverseOrder()).toList());
    }

    private List<Double> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        if (priceElements.isEmpty()) {
            throw new NoSuchElementException("Keine Produktpreise gefunden.");
        }
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            try {
                prices.add(Double.parseDouble(priceText));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Ungültiger Preisformat: " + priceText);
            }
        }

        return prices;
    }

    public boolean areProductsSortedAlphabetically() {
        List<String> productNames = getProductNames();
        List<String> sortedNames = new ArrayList<>(productNames);
        sortedNames.sort(String::compareToIgnoreCase);
        return productNames.equals(sortedNames);
    }
}
