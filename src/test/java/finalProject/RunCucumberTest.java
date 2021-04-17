package finalProject;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"finalProject.stepdefs"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}