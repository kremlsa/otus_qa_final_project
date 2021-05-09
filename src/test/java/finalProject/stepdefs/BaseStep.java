package finalProject.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.qameta.allure.Step;
import wtf.actions.web.Report;
import wtf.data.JsonCompare;
import wtf.data.JsonParse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static wtf.actions.Base.logger;

/**
 * Класс для описания базовых шагов Cucumber и для
 * связывания Cucumber и SpringBoot
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class BaseStep {

    private Report report = new Report();
    private static String jsonResult;
    private static String etalone;

    public static String getEtalone() {
        return etalone;
    }

    public static void setJsonResult(String jsonResult) {
        BaseStep.jsonResult = jsonResult;
    }

    @Step("Запускаем сценарий {scenarioName}")
    @И("Запускаем сценарий {string}")
    public void startScenario(String scenarioName) {
        logger.info("**********-- Запускаем сценарий - "
                + scenarioName +" --**********");
    }

    @И("Создать скриншот браузера {string}")
    public void makeScreenshot(String name) {
        report.takeScreenshot(name);
    }

    @И("добавляем JSON к отчёту")
    public void addJsonToReport() {
        report.addJsonToReport(jsonResult);
    }

    @И("объект Json для сравнения")
    public void createEtaloneJsonObject(DataTable data) {
        BaseStep.etalone = JsonParse.dataTableToJson(data);
    }

    @И("Json частично совпадает с эталонным")
    public void jsonLenientCompare() {
        JsonCompare.jsonLenientCompare(etalone,jsonResult);
    }
}
