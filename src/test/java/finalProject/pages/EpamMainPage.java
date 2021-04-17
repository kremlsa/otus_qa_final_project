package finalProject.pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EpamMainPage extends BasePage{

    @Value("${mainPage.CSSNavEvents}")
    private String navigationEvents;

    @Value("${mainPage.url}")
    private String url;

    public EpamMainPage open() {
        driver.get(url);
        return this;
    }

    public void openEvents() {
        System.out.println(navigationEvents);
        By navigationEventSelector = By.cssSelector(navigationEvents);
        driver.findElement(navigationEventSelector).click();
    }
}
