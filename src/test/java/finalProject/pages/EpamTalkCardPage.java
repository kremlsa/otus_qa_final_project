package finalProject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс для описания страницы с карточкой из раздела - Video
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
@Component
public class EpamTalkCardPage extends BasePage{

    @Value("${talkCardPage.divLocation}")
    private String location;

    @Value("${talkCardPage.divLanguage}")
    private String language;

    @Value("${talkCardPage.divTopics}")
    private String topics;

    @Value("${talkCardPage.hEvent}")
    private String event;

    public TalkCard parseCard(String targetUrl) {
        TalkCard testCard = new TalkCard();
        Selenide.open(targetUrl);
        ////div[@class='evnt-modal evnt-error-modal modal show']/div/div/div[3]/button[@class='evnt-button small'
        $x("//*[@id='onetrust-accept-btn-handler']").click();
        testCard.setEvent($x(event).getText());
        //testCard.setEvent(Selenide.title());
        testCard.setLocation($x(location).getText());
        testCard.setLanguage($x(language).getText());
        String topicName = "";
        for (SelenideElement element : $$x(topics)) {
            topicName += element.getText() + " ";
        }
        testCard.setCategory(topicName);
        Selenide.clearBrowserCookies();
        return testCard;
    }
}
