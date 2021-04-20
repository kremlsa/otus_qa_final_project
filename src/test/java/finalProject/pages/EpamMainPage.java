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
        Selenide.open(url);
        logger.info("Открываем раздел - " + Selenide.title());
        return this;
    }

    public void openEvents() {
        $(navigationEvents).click();
        logger.info("Открываем раздел - " + Selenide.title());
    }

    public EpamMainPage openTalks() {
        $(navigationTalks).click();
        logger.info("Открываем раздел - " + Selenide.title());
        return this;
    }


}
