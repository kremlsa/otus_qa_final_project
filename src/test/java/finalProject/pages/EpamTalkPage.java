package finalProject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import finalProject.common.TalkCard;
import finalProject.common.UniLoc;
import finalProject.common.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Component
public class EpamTalkPage extends BasePage {

    @Autowired
    private EpamTalkCardPage epamTalkCardPage;

    @Value("${talkPage.CSSInputSearch}")
    private String searchField;

    @Value("${talkPage.XPathSpanTalkTitle}")
    private String talkTitle;

    @Value("${talkPage.XPathSpanFilters}")
    private String moreFilters;

    @Value("${talkPage.XPathSpanLocation}")
    private String spanLocation;

    @Value("${talkPage.XPathSpanCategory}")
    private String spanCategory;

    @Value("${talkPage.XPathSpanLanguage}")
    private String spanLanguage;

    @Value("${talkPage.HrefCard}")
    private String cardLink;

    private String query = "";
    private final TalkCard etalone = new TalkCard();

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
                logger.warn(Utils.ANSI_RED + "Доклад - " + element.getText()
                        + " не содержит поисковый запрос - " + query);
                return false;
            } else {
                //Логируем
                logger.info(Utils.ANSI_GREEN + "Доклад - " + element.getText()
                        + " содержит поисковый запрос - " + query);
            }
        }
        return true;
    }

    //TO DO:
    //Вынести клик в базовый класс
    public EpamTalkPage clickMoreFilters() {
        $x(moreFilters).click();
        return this;
    }


    public void filterTesting(String category) {
        //Открываем список
        $x(spanCategory).click();
        //Устанавливаем эталонное значение
        etalone.setCategory(category);
        //Выбираем категорию
        $x(UniLoc.xpathString(UniLoc.LABELDATA, category)).click();

    }

    public void filterLocation(String location) {
        //Открываем список
        $x(spanLocation).click();
        //Устанавливаем эталонное значение
        etalone.setLocation(location);
        //Выбираем локацию
        $x(UniLoc.xpathString(UniLoc.LABELDATA, location)).click();
    }

    public void filterLanguage(String language) {
        //Открываем список
        $x(spanLanguage).click();
        //Устанавливаем эталонное значение
        etalone.setLanguage(language);
        //Выбираем язык
        $x(UniLoc.xpathString(UniLoc.LABELDATA, language)).click();
        //ждём пока прогрузится новый список тем (появится тэг)
        $x(spanLanguage).click();
        $x(UniLoc.xpathString(UniLoc.TAG, language)).should(Condition.exist);
    }

    public boolean isFilterWorks() {

        List<String> urls = new ArrayList<>();
        List<SelenideElement> elements = $$x(cardLink);
        for (SelenideElement element : elements) {
            urls.add(element.getAttribute("href"));
        }

        for (String url : urls) {
            TalkCard testCard = epamTalkCardPage.parseCard(url);
            if (!testCard.getCategory().contains(etalone.getCategory())) return false;
            if (!testCard.getLocation().contains(etalone.getLocation())) return false;
            if (!testCard.getLanguage().contains(etalone.getLanguage())) return false;
        }
        return true;
    }
}
