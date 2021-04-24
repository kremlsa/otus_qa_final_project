package finalProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class EventCard {

    private String cardLang = ".language span";
    private String cardEvent = ".evnt-event-name h1 span";
    private String cardDate = ".evnt-dates-cell span";
    private String cardReg = ".status";
    private String cardSpeakers = ".evnt-speaker";
    private String cardLink = ".evnt-event-card a";

    private String eventName = "Not defined";
    private String date = "Not defined";
    private String registration = "Not defined";
    private String lang = "Not defined";
    private final ArrayList<Speaker> speakers = new ArrayList<>();

    public void parse(WebElement card) {
        try {
            this.setCardLink(card.findElement(By.cssSelector(cardLink)).getAttribute("href"));
        } catch (Exception e) {
            System.out.println("CardLink not found");
        }
        try {
            this.setEventName(card.findElement(By.cssSelector(cardEvent)).getText());
        } catch (Exception e) {
            System.out.println("Name not found");
        }
        try {
            this.setLang(card.findElement(By.cssSelector(cardLang)).getText());
        } catch (Exception e) {
            System.out.println("Lang not found");
        }
        try {
            this.setDate(card.findElement(By.cssSelector(cardDate)).getText());
        } catch (Exception e) {
            System.out.println("Date not found");
        }
        try {
            this.setRegistration(card.findElement(By.cssSelector(cardReg)).getText());
        } catch (Exception e) {
            System.out.println("Reg not found");
        }
        try {
            List<WebElement> speakerElements = card.findElements(By.cssSelector(cardSpeakers));
            for (WebElement el : speakerElements) {
                this.addSpeakers(Speaker.parseSpeaker(el));
            }
        } catch (Exception e) {
            System.out.println("Speakers not found");
        }
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

    public void setCardLink(String cardLink) {
        this.cardLink = cardLink;
    }

    public String getCardLink() {
        return cardLink;
    }
}
