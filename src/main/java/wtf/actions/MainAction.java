package wtf.actions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.qameta.allure.Step;

public class MainAction extends BaseAction{
    /**
     * Метод для открытия URL или браузера
     *
     * @param url адрес для перехода String
     * @return текущий класс
     */
    @Step("Перейти по URL \"{url}\"")
    @Given("Перейти по URL {string}")
    public MainAction open(String url) {
        Selenide.open(url);
        logger.info("Открываем страницу " + url);
        return this;
    }

    /**
     * Метод для закрытия браузера
     *
     * @return текущий класс
     */
    @Step("Закрыть браузер")
    @Given("Закрыть браузер")
    public MainAction closeDriver() {
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
    @And("Очистить кэш и cookies браузера")
    public MainAction clearBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        logger.info("Чистим браузер.");
        return this;
    }
}
