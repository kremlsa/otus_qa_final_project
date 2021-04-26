package finalProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wtf.pom.BasePage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.codeborne.selenide.Selenide.$;
import static wtf.actions.Log.logInfo;
import static wtf.actions.Log.logWarn;

/**
 * Класс для представления карточек в разделе Events
 * в качестве объекта
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
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
    private final ArrayList<Speaker> speakers = new ArrayList<>();

    /**
     * Метод для парсинга объекта представляющего карточку мероприятия из элемента страницы
     *
     * @param card элемент для парсинга WebElement
     * @return представление карточки в виде объекта EventCard
     */
    public static EventCard parse(WebElement card) {
        EventCard newCard = new EventCard();
        if ($(By.cssSelector(cardLink)).exists()) {
            newCard.setLink(card.findElement(By.cssSelector(cardLink)).getAttribute("href"));
            logInfo("Парсинг карточки " + newCard.getLink());
        } else {
            logWarn("ссылка не найдена");
        }
        if ($(By.cssSelector(cardEvent)).exists()) {
            newCard.setEventName(card.findElement(By.cssSelector(cardEvent)).getText());
        } else {
            logWarn("событие не найдено");
        }
        if ($(By.cssSelector(cardLang)).exists()) {
            newCard.setLang(card.findElement(By.cssSelector(cardLang)).getText());
        } else {
            logWarn("язык не найден");
        }
        if ($(By.cssSelector(cardDate)).exists()) {
            newCard.setDate(card.findElement(By.cssSelector(cardDate)).getText());
        } else {
            logWarn("дата не найдена");
        }
        if ($(By.cssSelector(cardReg)).exists()) {
            newCard.setRegistration(card.findElement(By.cssSelector(cardReg)).getText());
        } else {
            System.out.println("регистрация не найдена");
        }
        if ($(By.cssSelector(cardSpeakers)).exists()) {
            List<WebElement> speakerElements = card.findElements(By.cssSelector(cardSpeakers));
            for (WebElement el : speakerElements) {
                newCard.addSpeakers(Speaker.parseSpeaker(el));
            }
        } else {
            System.out.println("докладчики не найдены");
        }
        return newCard;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getSpeakers() {
        return speakers.size() > 0 ? Arrays.toString(speakers.toArray()) : "Not defined";
    }

    public void addSpeakers(Speaker speaker) {
        this.speakers.add(speaker);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
