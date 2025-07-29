package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;   // Import Cucumber options for test configuration
import org.junit.runner.RunWith; // Import JUnit runner     
@RunWith(Cucumber.class) // Specify that this class is a Cucumber test runner
@CucumberOptions(
  plugin = {
    "pretty",
    "html:target/cucumber-reports.html", // HTML-Report wird erzeugt
    "json:target/cucumber.json" // Optional für JSON
  },
  features = "src/test/resources/features",
  glue = {"stepdefinitions", "utils"}
)
public class TestRunner {
    
}
