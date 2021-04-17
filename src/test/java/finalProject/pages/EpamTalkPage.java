package finalProject.pages;

import org.openqa.selenium.By;
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


}
