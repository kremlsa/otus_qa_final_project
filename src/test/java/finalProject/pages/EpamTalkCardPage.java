package finalProject.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import finalProject.common.TalkCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class EpamTalkCardPage extends BasePage{

    @Value("${talkCardPage.divLocation}")
    private String location;

    @Value("${talkCardPage.divLanguage}")
    private String language;

    @Value("${talkCardPage.divTopics}")
    private String topics;

    public TalkCard parseCard(String targetUrl) {
        TalkCard testCard = new TalkCard();
        //openURL(targetUrl);
        Selenide.open(targetUrl);
        //testCard.setLocation(driver.findElement(By.xpath(location)).getText());
        //testCard.setLanguage(driver.findElement(By.xpath(language)).getText());
        testCard.setLocation($x(location).getText());
        testCard.setLanguage($x(language).getText());
        String topicName = "";
        for (SelenideElement element : $$x(topics)) {
            topicName += element.getText() + " ";
        }
        testCard.setCategory(topicName);
        System.out.println(testCard.getCategory() + "---" + testCard.getLocation() + "---" + testCard.getLanguage());
        return testCard;
    }
}
