package finalProject.pages;

import finalProject.common.TalkCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EpamTalkCardPage extends BasePage{

    By location = By.xpath("//div[contains(@class,'location')]/span");
    By language = By.xpath("//div[contains(@class,'language')]/span");
    By topics = By.xpath("//div[contains(@class,'evnt-topic')]/label");

    public TalkCard parseCard(String targetUrl) {
        TalkCard testCard = new TalkCard();
        openURL(targetUrl);
        testCard.setLocation(driver.findElement(location).getText());
        testCard.setLanguage(driver.findElement(language).getText());
        String topicName = "";
        for (WebElement element : driver.findElements(topics)) {
            topicName += element.getText() + " ";
        }
        testCard.setCategory(topicName);
        System.out.println(testCard.getCategory() + " " + testCard.getLocation() + " " + testCard.getLanguage());
        return testCard;
    }
}
