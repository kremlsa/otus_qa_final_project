package finalProject.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EventCard {
    @Value("${eventCard.XPathCardPlace}")
    private String cardPlace;
    @Value("${eventCard.XPathCardLang}")
    private String cardLang;
    @Value("${eventCard.XPathCardEvent}")
    private String cardEvent;
    @Value("${eventCard.XPathCardDate}")
    private String cardDate;
    @Value("${eventCard.XPathCardReg}")
    private String cardReg;
    @Value("${eventCard.XPathCardSpeakers}")
    private String cardSpeakers;

    private String place = "Not defined";
    private String eventName = "Not defined";
    private String date = "Not defined";
    private String registration = "Not defined";
    private final ArrayList<Speaker> speakers = new ArrayList<>();

    public EventCard parse(SelenideElement card) {
        EventCard eventCard = new EventCard();
            try {
                eventCard.setEventName(card.findElement(By.xpath(cardEvent)).getText());
            } catch (Exception e) {
                System.out.println("Name not found");
            }
            try {
                eventCard.setDate(card.findElement(By.xpath(cardDate)).getText());
            } catch (Exception e) {
                System.out.println("Date not found");
            }
            try {
                eventCard.setRegistration(card.findElement(By.xpath(cardReg)).getText());
            } catch (Exception e) {
                System.out.println("Reg not found");
            }
            try {
                List<WebElement> speakerElements = card.findElements(By.xpath(cardSpeakers));
                for (WebElement el : speakerElements) {
                    eventCard.addSpeakers(Speaker.parseSpeaker(el));
                }
            } catch (Exception e) {
                System.out.println("Speakers not found");
            }
            System.out.println(eventCard.getDate() + " - " + eventCard.getEventName() + " - " + eventCard.getPlace()
                    + " - " + eventCard.getRegistration() + " - " + eventCard.getSpeakers());

        return eventCard;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
        return speakers.toString();
    }

    public void addSpeakers(Speaker speaker) {
        this.speakers.add(speaker);
    }
}
