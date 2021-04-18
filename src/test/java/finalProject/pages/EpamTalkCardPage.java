package finalProject.pages;

import finalProject.common.TalkCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        openURL(targetUrl);
        testCard.setLocation(driver.findElement(By.xpath(location)).getText());
        testCard.setLanguage(driver.findElement(By.xpath(language)).getText());
        String topicName = "";
        for (WebElement element : driver.findElements(By.xpath(topics))) {
            topicName += element.getText() + " ";
        }
        testCard.setCategory(topicName);
        System.out.println(testCard.getCategory() + " " + testCard.getLocation() + " " + testCard.getLanguage());
        return testCard;
    }
}
