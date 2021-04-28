package finalProject.pages;

import finalProject.common.Utils;
import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wtf.pom.BasePage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Класс для представления карточек в разделе Events
 * в качестве объекта
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventCard extends BasePage {

    private  static String cardLang = ".language span";
    private  static String cardEvent = ".evnt-event-name h1 span";
    private  static String cardDate = ".evnt-dates-cell span";
    private  static String cardReg = ".status";
    private  static String cardSpeakers = ".evnt-speaker";
    private  static String cardLink = ".evnt-event-card a";

    private String eventName = "Not defined";
    private String date = "Not defined";
    private String registration = "Not defined";
    private String lang = "Not defined";
    private String link = "Not defined";
    private List<Speaker> speakers = new ArrayList<>();

    /**
     * Метод для парсинга объекта представляющего карточку мероприятия из элемента страницы
     *
     * @param card элемент для парсинга WebElement
     * @return представление карточки в виде объекта EventCard
     */
    public static EventCard parse(WebElement card) {
        return new EventCard()
                    .builder()
                    .date(Utils.textOrNotDefined(card, By.cssSelector(cardDate)))
                    .lang(Utils.textOrNotDefined(card, By.cssSelector(cardLang)))
                    .eventName(Utils.textOrNotDefined(card, By.cssSelector(cardEvent)))
                    .registration(Utils.textOrNotDefined(card, By.cssSelector(cardReg)))
                    .speakers(Utils.speakersOrNotDefined(card, By.cssSelector(cardSpeakers)))
                    .build();
    }

    public boolean isFieldsFill() {
        return  !(getSpeakerFromList().equals("Not defined") || registration.equals("Not defined")
                || date.equals("Not defined") || lang.equals("Not defined")
                || eventName.equals("Not defined"));
    }

    public String getSpeakerFromList() {
        return speakers.size() > 0 ? Arrays.toString(speakers.toArray()) : "Not defined";
    }

    public void addSpeakerToList(Speaker speaker) {
        this.speakers.add(speaker);
    }

    public void loggingCard() {
        logger.info("Карточка {} поле event - {}", eventName, eventName);
        logger.info("Карточка {} поле date - {}", eventName, date);
        logger.info("Карточка {} поле reg - {}", eventName, registration);
        logger.info("Карточка {} поле lang - {}", eventName, lang);
    }
}
