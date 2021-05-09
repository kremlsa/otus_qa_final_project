package finalProject.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import wtf.pom.BasePage;

/**
 * Класс для описания страницы с карточкой из раздела - Video
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamTalkCardPage extends BasePage {

    //Локаторы
    private String location = "//div[contains(@class,'location')]/span";
    private String language = "//div[contains(@class,'language')]/span";
    private String topics = "//div[contains(@class,'evnt-topic')]/label";
    private String event = "//h1[@class='evnt-talk-title']";

    /**
     * Метод для парсинга объекта представляющего карточку мероприятия по ссылке
     *
     * @param targetUrl ссылка для парсинга карточки
     * @return представление карточки в виде объекта TalkCard
     */
    public TalkCard parseCard(String targetUrl) {
        action.open(targetUrl);

        //Составляем топики
        String topicName = "";
        for (SelenideElement element : find.listLoc(By.xpath(topics))) {
            topicName += element.getText() + " ";
        }
        TalkCard testCard = TalkCard.builder()
                .event(find.locText(By.xpath(event)))
                .location(find.locText(By.xpath(location)))
                .language(find.locText(By.xpath(language)))
                .category(topicName)
                .build();
        return testCard;
    }
}
