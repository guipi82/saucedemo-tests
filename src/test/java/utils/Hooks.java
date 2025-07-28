package utils;



import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Hooks class to manage WebDriver lifecycle for Cucumber tests.
 * Hooks.java liest den Browser dynamisch
 */
public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        // Browser als JVM-Parameter lesen (default: chrome)
            String browser = System.getProperty("browser", "chromeheadless"); // Default to Chrome if not specified
            driver = DriverFactory.createDriver(browser); // Create WebDriver instance using DriverFactory      
 
    }

    @After
    public void tearDown() {
        // Quit the WebDriver after each scenario
        DriverFactory.quitDriver(driver); // Ensure the driver is quit after each scenario
    }


    public static WebDriver getDriver() {
        return driver;
    }  

    

}
