package finalProject.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import wtf.pom.BasePage;
import wtf.uniloc.UniLoc;

/**
 * Класс для описания страницы - Video
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamTalkPage extends BasePage {

    //Локаторы
    private final String SEARCH_FIELD = "input.evnt-search[type=text]";
    private final String TALK_TITLE = "//div[@class='evnt-talk-name']/h1/span";
    private final String MORE_FILTERS = "//span[contains(text(),'More Filters')]";
    private final String SPAN_CATEGORY = "//span[contains(text(),'Category')]";
    private final String SPAN_LOCATION = "//span[contains(text(),'Location')]";
    private final String SPAN_LANGUAGE = "//span[contains(text(),'Language')]";
    private final String CARD_LINK = "//*[@class='evnt-talk-card']/a";

    private EpamTalkCardPage epamTalkCardPage = new EpamTalkCardPage();
    private final TalkCard etalone = new TalkCard();

    private String query = "";

    /**
     * Метод для заполнения формы поиска на странице Video
     *
     */
    public void fillSearch(String query) {
        this.query = query;
        //Запоминаем текст текущего элемента из списка тем и значение
        String elementText = find.locText(By.xpath(TALK_TITLE));
        //Заполняем поле ввода
        input.locatorEnter(By.cssSelector(SEARCH_FIELD), query)
                .log("Выполняем поисковый запрос с параметром  - " + query);
        //ждём пока прогрузится новый список тем
        wait.disappearText(By.xpath(TALK_TITLE), elementText, 5000);
    }

    /**
     * Метод для проверки работы фильтра по ключевому слову
     *
     *  @return результат проверки boolean
     */
    public boolean checkTalkTitle() {
        //Проверяем темы докладов
        return find.isTextInEachElements(By.xpath(TALK_TITLE), query);
    }

    /**
     * Метод для открытия списка фильтров
     *
     *  @return текущий класс
     */
    public EpamTalkPage clickMoreFilters() {
        click.xpathLocator(MORE_FILTERS)
                .log("Выбираем больше вариантов фильтрации - More Filters");
        return this;
    }

    /**
     * Метод для выбора категории
     *
     */
    public void filterCategory(String category) {
        //Открываем список, выбираем категорию
        click.xpathLocator(SPAN_CATEGORY)
                .xpathLocator(UniLoc.xpathString(UniLoc.LABELDATA, category))
                .log("Выбираем значение фильтра Category " + category);
        //Устанавливаем эталонное значение
        etalone.setCategory(category);
    }

    /**
     * Метод для выбора локации
     *
     */
    public void filterLocation(String location) {
        //Открываем список, выбираем локацию
        click.xpathLocator(SPAN_LOCATION)
                .xpathLocator(UniLoc.xpathString(UniLoc.LABELDATA, location))
                .log("Выбираем значение фильтра Location " + location);
        //Устанавливаем эталонное значение
        etalone.setLocation(location);
    }

    /**
     * Метод для выбора языка
     *
     */
    public void filterLanguage(String language) {
        //Открываем список, выбираем язык
        click.xpathLocator(SPAN_LANGUAGE)
                .xpathLocator(UniLoc.xpathString(UniLoc.LABELDATA, language))
                .log("Выбираем значение фильтра Language " + language);
        //Устанавливаем эталонное значение
        etalone.setLanguage(language);
        //ждём пока прогрузится новый список тем (появится тэг)
        click.xpathLocator(SPAN_LANGUAGE);
        wait.exist(UniLoc.xpathLocator(UniLoc.TAG, language));
    }

    /**
     * Метод для проверки работы фильтра
     *
     * @return результат проверки boolean
     */
    public boolean isFilterWorks() {
        find.attributesList(By.xpath(CARD_LINK), "href").stream()
                .filter(x -> !x.contains("1621"))//Баг на селеноиде исключаем страницу из проверки
                .map(x -> epamTalkCardPage.parseCard(x))
                .forEach(x -> Assert.assertTrue(x.equals(etalone),
                        "Карточка " + x.getEvent() + " не отвечает критериям фильтра"));
        return true;
    }
}
