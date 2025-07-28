package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By addToCartButton = By.id("add-to-cart");
    private By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    private By burgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By resetAppStateLink = By.id("reset_sidebar_link");
    private By sortDropdown = By.cssSelector("select[data-test='product-sort-container']");
    private By productNames = By.xpath("//div[@data-test='inventory-item-name']");

    public void openProductsPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    public void addProductToCart(String productName) {
        String normalizedName = productName.toLowerCase().replace(" ", "-");
        String selector = "[data-test='add-to-cart-" + normalizedName + "']";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        driver.findElement(By.cssSelector(selector)).click();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public void clickOnProductByName(String productName) {
        By productLocator = By.xpath("//div[normalize-space()='" + productName + "']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        driver.findElement(productLocator).click();
    }

    public String getProductPrice(String productName) {
        By priceLocator = By.xpath(
            "//div[@data-test='inventory-item-name' and normalize-space(text())='" + productName +
            "']/ancestor::div[@data-test='inventory-item-description']//div[@data-test='inventory-item-price']"
        );
        return driver.findElement(priceLocator).getText();
    }

    public boolean isProductDisplayed(String productName) {
        try {
            By productLocator = By.xpath("//div[contains(@class, 'inventory_item_name') and normalize-space(text())='" + productName + "']");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
            return driver.findElement(productLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnBurgerMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu));
        driver.findElement(burgerMenu).click();
    }

    public void clickOnLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        driver.findElement(logoutLink).click();
    }

    public void clickOnAbout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        driver.findElement(aboutLink).click();
    }

    public void clickOnResetAppState() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetAppStateLink));
        driver.findElement(resetAppStateLink).click();
    }

    public boolean assertAboutPageVisible() {
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (driver.getCurrentUrl().contains("saucelabs.com")) {
                return true;
            }
        }
        return false;
    }

    public void sortProducts(String sortOption) {
        Select dropdown = new Select(driver.findElement(sortDropdown));
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
                throw new IllegalArgumentException("Ungültiges Preisformat: " + priceText);
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
