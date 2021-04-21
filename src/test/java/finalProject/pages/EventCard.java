package finalProject.pages;

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
    @Value("${eventCard.XPathCardHref}")
    private String cardLink;

    private String place = "Not defined";
    private String eventName = "Not defined";
    private String date = "Not defined";
    private String registration = "Not defined";
    private String lang = "Not defined";
    private final ArrayList<Speaker> speakers = new ArrayList<>();

    public EventCard parse(WebElement card) {
        EventCard eventCard = new EventCard();
        try {
            eventCard.setCardLink(card.findElement(By.xpath(cardLink)).getAttribute("href"));
        } catch (Exception e) {
            System.out.println("CardLink not found");
        }
        try {
            eventCard.setPlace(card.findElement(By.xpath(cardPlace)).getText());
        } catch (Exception e) {
            System.out.println("Place not found");
        }
        try {
            eventCard.setEventName(card.findElement(By.xpath(cardEvent)).getText());
        } catch (Exception e) {
            System.out.println("Name not found");
        }
        try {
            eventCard.setLang(card.findElement(By.xpath(cardLang)).getText());
        } catch (Exception e) {
            System.out.println("Lang not found");
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
