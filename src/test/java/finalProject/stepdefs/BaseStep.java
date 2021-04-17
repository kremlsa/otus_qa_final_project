package finalProject.stepdefs;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import finalProject.FinalSpringApplication;
import finalProject.configuration.Cfg;

@ContextConfiguration(classes = Cfg.class)
@CucumberContextConfiguration
@SpringBootTest(classes = FinalSpringApplication.class)
public class BaseStep {
}
