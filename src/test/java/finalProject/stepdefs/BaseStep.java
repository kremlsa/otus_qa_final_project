package finalProject.stepdefs;

import finalProject.common.BaseClass;
import finalProject.common.Utils;
import io.cucumber.java.ru.И;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import finalProject.FinalSpringApplication;
import finalProject.configuration.Cfg;

@ContextConfiguration(classes = Cfg.class)
@CucumberContextConfiguration
@SpringBootTest(classes = FinalSpringApplication.class)
public class BaseStep {
    @И("Запускаем сценарий {string}")
    public void startScenario(String scenarioName) {
        BaseClass.getLogger().info(Utils.ANSI_PURPLE + "Запускаем сценарий - " + scenarioName);
    }
}
