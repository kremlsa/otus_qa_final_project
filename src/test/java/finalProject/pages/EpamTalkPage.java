package finalProject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import wtf.uniloc.UniLoc;
import org.openqa.selenium.Keys;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

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

    public void fillSearch(String query) {
        this.query = query;
        //Запоминаем текущий элемент из списка тем и значение
        SelenideElement element = $x(talkTitle);
        String elementText = element.getText();
        //Заполняем поле ввода
        $(searchField).sendKeys(query, Keys.ENTER);
        logger.info("Выполняем поисковый запрос с параметром  - " + query);
        //ждём пока прогрузится новый список тем
        element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);
    }

    public boolean checkTalkTitle() {
        //Проверяем темы докладов
        for (SelenideElement element : $$x(talkTitle)) {
            if (!element.getText().contains(query)) {
                //Логируем
                logger.warn("Доклад - " + element.getText()
                        + " не содержит поисковый запрос - " + query);
                return false;
            } else {
                //Логируем
                logger.info("Доклад - " + element.getText()
                        + " содержит поисковый запрос - " + query);
            }
        }
        return true;
    }

    public EpamTalkPage clickMoreFilters() {
        $x(moreFilters).click();
        //Логируем
        logger.info("Выбираем больше вариантов фильтрации - More Filters");
        return this;
    }


    public void filterCategory(String category) {
        //Открываем список
        $x(spanCategory).click();
        //Устанавливаем эталонное значение
        etalone.setCategory(category);
        //Выбираем категорию
        $x(UniLoc.xpathString(UniLoc.LABELDATA, category)).click();
        //Логируем
        logger.info("Выбираем значение фильтра Category " + category);

    }

    public void filterLocation(String location) {
        //Открываем список
        $x(spanLocation).click();
        //Устанавливаем эталонное значение
        etalone.setLocation(location);
        //Выбираем локацию
        $x(UniLoc.xpathString(UniLoc.LABELDATA, location)).click();
        //Логируем
        logger.info("Выбираем значение фильтра Location " + location);
    }

    public void filterLanguage(String language) {
        //Открываем список
        $x(spanLanguage).click();
        //Устанавливаем эталонное значение
        etalone.setLanguage(language);
        //Выбираем язык
        $x(UniLoc.xpathString(UniLoc.LABELDATA, language)).click();
        //Логируем
        logger.info("Выбираем значение фильтра Language " + language);
        //ждём пока прогрузится новый список тем (появится тэг)
        $x(spanLanguage).click();
        $x(UniLoc.xpathString(UniLoc.TAG, language)).should(Condition.exist);
    }

    public boolean isFilterWorks() {
        //Список ссылок для проверки результатов фильтрации
        List<String> urls = new ArrayList<>();
        //Список карточек по результатам фильтрации
        List<SelenideElement> elements = $$x(cardLink);
        //Составляем список ссылок
        for (SelenideElement element : elements) {
            urls.add(element.getAttribute("href"));
        }
        //Проверяем результаты через вспомогательный объект и логируем
        for (String url : urls) {
            //Убираем из тестов страницу вызывающую баг на селеноиде
            if (url.contains("1621")) continue;
            //продолжаем тест
            TalkCard testCard = epamTalkCardPage.parseCard(url);
            if (!testCard.getCategory().contains(etalone.getCategory())) {
                logger.warn("категория " + testCard.getCategory()
                        + " в карточке " + testCard.getEvent()
                    + " не совпадает с заданной " + etalone.getCategory());
                return false;
            } else {
                logger.info("категория " + testCard.getCategory()
                        + " в карточке " + testCard.getEvent()
                        + " совпадает с заданной " + etalone.getCategory());
            }
            if (!testCard.getLocation().contains(etalone.getLocation())) {
                logger.warn("локация " + testCard.getLocation()
                        + " в карточке " + testCard.getEvent()
                        + " не совпадает с заданной " + etalone.getLocation());
                return false;
            } else {
                logger.info("локация " + testCard.getLocation()
                        + " в карточке " + testCard.getEvent()
                        + " совпадает с заданной " + etalone.getLocation());
            }
            if (!testCard.getLanguage().contains(etalone.getLanguage())) {
                logger.warn("язык " + testCard.getLanguage()
                        + " в карточке " + testCard.getEvent()
                        + " не совпадает с заданной " + etalone.getLanguage());
                return false;
            } else {
                logger.info("язык " + testCard.getLanguage()
                        + " в карточке " + testCard.getEvent()
                        + " совпадает с заданной " + etalone.getLanguage());
            }
        }
        return true;
    }
}
