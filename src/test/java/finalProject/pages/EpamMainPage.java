package finalProject.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class EpamMainPage extends BasePage{

    @Value("${mainPage.CSSNavEvents}")
    private String navigationEvents;

    @Value("${mainPage.CSSNavTalks}")
    private String navigationTalks;

    @Value("${mainPage.url}")
    private String url;

    public EpamMainPage open() {
        //driver.get(url); --- Delete
        Selenide.open(url);
        return this;
    }

    public void openEvents() {
        System.out.println(navigationEvents);
        By navigationEventSelector = By.cssSelector(navigationEvents);
        driver.findElement(navigationEventSelector).click();
    }

    public EpamMainPage openTalks() {
        //System.out.println(navigationTalks); --- Delete
        //By navigationEventSelector = By.cssSelector(navigationTalks); --- Delete
        //driver.findElement(navigationEventSelector).click(); --- Delete
        $(navigationTalks).click();
        return this;
    }


}
