package wtf.actions.web;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import wtf.actions.Base;

/**
 * Класс для основных действий с драйвером
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Action extends Base {
    /**
     * Метод для открытия URL или браузера
     *
     * @param url адрес для перехода String*
     * @return текущий класс
     */
    @Step("Перейти по URL \"{url}\"")
    public Action open(String url) {
        Selenide.open(url);
        logger.info("Открываем страницу {}", url);
        return this;
    }

    /**
     * Метод для закрытия браузера
     *
     * @return текущий класс
     */
    @Step("Закрыть браузер")
    public Action closeDriver() {
        Selenide.closeWebDriver();
        logger.info("Закрыт браузер");
        return this;
    }

    /**
     * Метод для очистки кэша и cookies браузера
     *
     * @return текущий класс
     */
    @Step("Очистить кэш и cookies браузера")
    public Action clearBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        logger.info("Чистим браузер.");
        return this;
    }
}
