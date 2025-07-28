package utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager for managing browser drivers

/**
 * DriverFactory is a utility class that provides methods to initialize and
 * manage WebDriver instances.
 */

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();// ThreadLocal to ensure each thread has its own
                                                                       // WebDriver instance

    // Erstellt neuen WebDriver für jeden Thread/Szenario
    public static WebDriver createDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
            case "chromeheadless":
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-save-password-bubble");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);

                if (browser.equalsIgnoreCase("chromeheadless")) {
                    options.addArguments("--headless=new"); // headless mode (new engine, ab Chrome 109+)
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080"); // Pflicht bei Headless!
                }

                    driver.set(new ChromeDriver(options));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        // Maximieren für bessere Sichtbarkeit
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static WebDriver getDriver() {
        // Return the current WebDriver instance
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call createDriver() first.");
        }
        return driver.get();
    }

    // Method to quit the WebDriver instance
    public static void quitDriver(WebDriver webDriver) {
        if (driver.get() != null) {
            driver.get().quit(); // Quit the existing driver if it exists
            driver.remove();
        }

    }

}
