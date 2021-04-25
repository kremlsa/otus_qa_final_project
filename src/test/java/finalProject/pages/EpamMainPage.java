package finalProject.pages;

import com.codeborne.selenide.Selenide;
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
    private String navigationEvents = "a.nav-link[href='/events']";
    private String navigationTalks = "a.nav-link[href*='/video']";

    public EpamMainPage open() {
        Selenide.open(url);
        logger.info("Открываем раздел - " + Selenide.title());
        return this;
    }

    public void openEvents() {
        $(navigationEvents).click();
        logger.info("Открываем раздел - " + Selenide.title());
    }

    public EpamMainPage openTalks() {
        $(navigationTalks).click();
        logger.info("Открываем раздел - " + Selenide.title());
        return this;
    }


}
