package finalProject.stepdefs;

import io.cucumber.java.ru.И;
import io.qameta.allure.Step;
import wtf.actions.Log;

/**
 * Класс для описания базовых шагов Cucumber и для
 * связывания Cucumber и SpringBoot
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class BaseStep {

    @Step("Запускаем сценарий {scenarioName}")
    @И("Запускаем сценарий {string}")
    public void startScenario(String scenarioName) {
        Log.logInfo("**********-- Запускаем сценарий - "
                + scenarioName +" --**********");
    }
}
