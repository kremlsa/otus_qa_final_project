package wtf.actions.web;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import wtf.actions.Base;

import java.io.*;

/**
 * Класс для действий с системой отчётности Allure
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Report extends Base {
    /**
     * Метод прикрепления скриншота браузера к отчёту Allure
     *
     * @param name наименование скриншота без указания расширения
     */
    public void takeScreenshot(String name) {
        File screenShot = new File(Selenide.screenshot(name));
        String nameScreenshotWithExtension = name.concat(".png");
        InputStream screenshotByte = null;
        try {
            screenshotByte = new BufferedInputStream(new FileInputStream(screenShot));
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        Allure.addAttachment(nameScreenshotWithExtension, screenshotByte);
    }

    /**
     * Метод прикрепления скриншота браузера к отчёту Allure
     *
     * @param jsonString json в строковом представлении String
     */
    public void addJsonToReport(String jsonString) {
        Allure.addAttachment("Результат", "application/json", jsonString);
    }
}
