package finalProject.pages;

import finalProject.common.UniLoc;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EpamTalkPage extends BasePage {

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

    private final String query = "QA";


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
        driver.findElement(By.xpath(spanCategory)).click();
        scrollAndClick(driver.findElement(UniLoc.xpathLocator(UniLoc.LABELCONTAINS, category)));
    }

    public void filterLocation(String location) {

        driver.findElement(By.xpath(spanLocation)).click();
        scrollAndClick(driver.findElement(UniLoc.xpathLocator(UniLoc.LABELCONTAINS, location)));
    }

    public void filterLanguage(String language) {
        driver.findElement(By.xpath(spanLanguage)).click();
        scrollAndClick(driver.findElement(UniLoc.xpathLocator(UniLoc.LABELCONTAINS, language)));
    }
}
