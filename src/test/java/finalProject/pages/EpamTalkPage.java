package finalProject.pages;

import org.openqa.selenium.By;
import wtf.uniloc.UniLoc;
import java.util.List;
import static wtf.actions.Log.logInfo;
import static wtf.actions.Log.logWarn;

/**
 * Класс для описания страницы - Video
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamTalkPage extends BasePage {

    //Локаторы
    private String searchField = "input.evnt-search[type=text]";
    private String talkTitle = "//div[@class='evnt-talk-name']/h1/span";
    private String moreFilters = "//span[contains(text(),'More Filters')]";
    private String spanCategory = "//span[contains(text(),'Category')]";
    private String spanLocation = "//span[contains(text(),'Location')]";
    private String spanLanguage = "//span[contains(text(),'Language')]";
    private String cardLink = "//*[@class='evnt-talk-card']/a";

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
        String elementText = find.locText(By.xpath(talkTitle));
        //Заполняем поле ввода
        input.locatorEnter(By.cssSelector(searchField), query)
                .log("Выполняем поисковый запрос с параметром  - " + query);
        //ждём пока прогрузится новый список тем
        wait.disappearText(By.xpath(talkTitle), elementText, 5000);
    }

    /**
     * Метод для проверки работы фильтра по ключевому слову
     *
     *  @return результат проверки boolean
     */
    public boolean checkTalkTitle() {
        //Проверяем темы докладов
        return find.isTextInEachElements(By.xpath(talkTitle), query);
    }

    /**
     * Метод для открытия списка фильтров
     *
     *  @return текущий класс
     */
    public EpamTalkPage clickMoreFilters() {
        click.xpathLocator(moreFilters)
                .log("Выбираем больше вариантов фильтрации - More Filters");
        return this;
    }

    /**
     * Метод для выбора категории
     *
     */
    public void filterCategory(String category) {
        //Открываем список, выбираем категорию
        click.xpathLocator(spanCategory)
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
        click.xpathLocator(spanLocation)
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
        click.xpathLocator(spanLanguage)
                .xpathLocator(UniLoc.xpathString(UniLoc.LABELDATA, language))
                .log("Выбираем значение фильтра Language " + language);
        //Устанавливаем эталонное значение
        etalone.setLanguage(language);
        //ждём пока прогрузится новый список тем (появится тэг)
        click.xpathLocator(spanLanguage);
        wait.exist(UniLoc.xpathLocator(UniLoc.TAG, language));
    }

    /**
     * Метод для проверки работы фильтра
     *
     * @return результат проверки boolean
     */
    public boolean isFilterWorks() {
        //Список ссылок для проверки результатов фильтрации
        List<String> urls = find.attributesList(By.xpath(cardLink), "href");
        //Проверяем результаты через вспомогательный объект и логируем
        for (String url : urls) {
            //Убираем из тестов страницу вызывающую баг на селеноиде
            if (url.contains("1621")) continue;
            //продолжаем тест
            TalkCard testCard = epamTalkCardPage.parseCard(url);
            if (!testCard.getCategory().contains(etalone.getCategory())) {
                logWarn("категория " + testCard.getCategory()
                        + " в карточке " + testCard.getEvent()
                    + " не совпадает с заданной " + etalone.getCategory());
                return false;
            } else {
                logInfo("категория " + testCard.getCategory()
                        + " в карточке " + testCard.getEvent()
                        + " совпадает с заданной " + etalone.getCategory());
            }
            if (!testCard.getLocation().contains(etalone.getLocation())) {
                logWarn("локация " + testCard.getLocation()
                        + " в карточке " + testCard.getEvent()
                        + " не совпадает с заданной " + etalone.getLocation());
                return false;
            } else {
                logInfo("локация " + testCard.getLocation()
                        + " в карточке " + testCard.getEvent()
                        + " совпадает с заданной " + etalone.getLocation());
            }
            if (!testCard.getLanguage().contains(etalone.getLanguage())) {
                logWarn("язык " + testCard.getLanguage()
                        + " в карточке " + testCard.getEvent()
                        + " не совпадает с заданной " + etalone.getLanguage());
                return false;
            } else {
                logInfo("язык " + testCard.getLanguage()
                        + " в карточке " + testCard.getEvent()
                        + " совпадает с заданной " + etalone.getLanguage());
            }
        }
        return true;
    }
}
