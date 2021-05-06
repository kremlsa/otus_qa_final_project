package finalProject.pages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.testng.Assert;
import wtf.pom.BasePage;
import wtf.uniloc.UniLoc;

import java.util.stream.Collectors;

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
     * Метод для проверки работы фильтра
     *
     * @param table таблица значений параметров фильтра DataTable
     * @return результат проверки boolean
     */
    public boolean isFilterWorks(DataTable table) {
        //Задаём эталонную карточку
        TalkCard etalone = table.cells()
                .stream()
                .map(fields -> new TalkCard(fields.get(0), fields.get(1), fields.get(2), fields.get(3)))
                .collect(Collectors.toList()).get(0);
        //Проверяем совпадение с заданными параметрами фильтрации
        find.attributesList(By.xpath(CARD_LINK), "href").stream()
                .filter(x -> !x.contains("1621"))//Баг на селеноиде исключаем страницу из проверки
                .map(x -> epamTalkCardPage.parseCard(x))
                .forEach(x -> Assert.assertTrue(x.equals(etalone),
                        "Карточка " + x.getEvent() + " не отвечает критериям фильтра"));
        logger.info("Карточки отвечают критериям фильтра");
        return true;
    }
}
