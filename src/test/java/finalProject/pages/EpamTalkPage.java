package finalProject.pages;

import finalProject.common.TalkCard;
import finalProject.common.UniLoc;
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

    private final String query = "QA";
    private final TalkCard etalone = new TalkCard();

    public void fillSearch() {
        //Запоминаем текущие элементы из списка тем
        List<WebElement> elements = driver.findElements(By.xpath(talkTitle));
        //Заполняем поле ввода
        driver.findElement(By.cssSelector(searchField))
                .sendKeys(query, Keys.ENTER);
        //ждём пока прогрузится новый список тем
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOf(elements.get(elements.size() - 1)));
    }

    public boolean checkTalkTitle() {
        //Если тема элемента не содержат искомую фразу то возвращаем false
        for (WebElement element : driver.findElements(By.xpath(talkTitle))) {
            System.out.println(element.getText());
            if (!element.getText().contains(query)) return false;
        }
        return true;
    }

    //TO DO:
    //Вынести клик в базовый класс
    public void clickMoreFilters() {
        driver.findElement(By.xpath(moreFilters)).click();
    }


    public void filterTesting(String category) {
        //Открываем список
        driver.findElement(By.xpath(spanCategory)).click();
        //Устанавливаем эталонное значение
        etalone.setCategory(category);
        //Выбираем категорию
        scrollAndClick(driver.findElement(UniLoc.xpathLocator(UniLoc.LABELDATA, category)));

    }

    public void filterLocation(String location) {
        //Открываем список
        driver.findElement(By.xpath(spanLocation)).click();
        //Устанавливаем эталонное значение
        etalone.setLocation(location);
        //Выбираем локацию
        scrollAndClick(driver.findElement(UniLoc.xpathLocator(UniLoc.LABELDATA, location)));
    }

    public void filterLanguage(String language) {
        //Открываем список
        driver.findElement(By.xpath(spanLanguage)).click();
        //Устанавливаем эталонное значение
        etalone.setLanguage(language);
        //Выбираем язык
        scrollAndClick(driver.findElement(UniLoc.xpathLocator(UniLoc.LABELDATA, language)));
    }

    public boolean isFilterWorks() {
        List<String> urls = new ArrayList<>();

        List<WebElement> elements = driver.findElements(By.xpath(cardLink));
        for (WebElement element : elements) {
            try {
                System.out.println("*************" + element.getAttribute("href"));
                urls.add(element.getAttribute("href"));
            } catch (Exception e) {
                System.out.println("*************");
            }
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
