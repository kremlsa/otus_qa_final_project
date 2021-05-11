package finalProject.pages;

import finalProject.stepdefs.BaseStep;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.testng.Assert;
import wtf.data.JsonCompare;
import wtf.data.JsonParse;
import wtf.pom.BasePage;
import wtf.uniloc.UniLoc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
     * @return результат проверки boolean
     */
    public boolean isFilterWorks() {
        //Задаём эталонную карточку
        String etalone = BaseStep.getEtalone();

        //Создаём список из json представлений карточек
        List<String> jsons = find.attributesList(By.xpath(CARD_LINK), "href").stream()
                .filter(x -> !x.contains("1621"))//Баг на селеноиде исключаем страницу из проверки
                .map(x -> epamTalkCardPage.parseCard(x))
                .map(JsonParse::objectToJson)
                .collect(Collectors.toList());

        //Проверяем совпадение с заданными параметрами фильтрации
        jsons.forEach(x -> JsonCompare.jsonContainsCompare(x, etalone));
        logger.info("Карточки отвечают критериям фильтра");

        //Добавляем к отчёту карточки
        String jsonResult = jsons.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",\n"));
        jsonResult = "[" + jsonResult + "]";
        BaseStep.setJsonResult(jsonResult);

        return true;
    }
}
