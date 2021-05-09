package finalProject.pages;

import wtf.pom.BasePage;

/**
 * Класс для описания главной страницы сайта EPAM
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamMainPage extends BasePage {

    private String url = "https://events.epam.com";
    //Локаторы
    private String navigationEvents = "//a[@class='nav-link' and @href='/events']";
    private String navigationTalks = "//a[contains(@href,'video') and @class='nav-link']";

    /**
     * Метод для открытия главной страницы сайте EPAM
     *
     * @return текущий класс
     */
    public EpamMainPage open() {
        action.open(url)
                .logTitle();
        //Соглашаемся с кукис
        click.xpathLocator("//*[@id='onetrust-accept-btn-handler']");
        return this;
    }

    /**
     * Метод для открытия раздела Events сайте EPAM
     *
     */
    public void openEvents() {
        click.xpathLocator(navigationEvents)
                .logTitle();

    }

    /**
     * Метод для открытия раздела Talks на сайте EPAM
     *
     */
    public void openTalks() {
        click.xpathLocator(navigationTalks)
                .logTitle();
    }
}
