package finalProject.pages;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для описания главной страницы сайта EPAM
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamMainPage extends BasePage{

    private String url = "https://events.epam.com";
    //Локаторы
    private String navigationEvents = "//a[@class='nav-link' and @href='/events']";
    private String navigationTalks = "//a[contains(@href,'video') and @class='nav-link']";

    public EpamMainPage open() {
        action.open(url);
        logger.info("Открываем раздел - " + Selenide.title());
        return this;
    }

    public void openEvents() {
        click.xpathLocator(navigationEvents);
        action.logTitle();
    }

    public void openTalks() {
        click.xpathLocator(navigationTalks);
        action.logTitle();
    }


}
