package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;   // Import Cucumber options for test configuration
import org.junit.runner.RunWith; // Import JUnit runner     
@RunWith(Cucumber.class) // Specify that this class is a Cucumber test runner
@CucumberOptions(
    features = "src/test/resources/features", // Path to the feature files
    glue = {"stepdefinitions", "utils"}, // Package containing step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html"}, // Plugins for reporting
    monochrome = true// Make console output more readable
)
public class TestRunner {
    
}
