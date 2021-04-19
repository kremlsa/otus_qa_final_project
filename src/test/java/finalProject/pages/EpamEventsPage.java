package finalProject.pages;

import finalProject.common.UniLoc;
import finalProject.common.Utils;
import io.cucumber.java.lv.Un;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class EpamEventsPage extends BasePage{

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Value("${eventsPage.XPathUpcomingEvents}")
    private String upcomingEvents;
    @Value("${eventsPage.XPathPastEvents}")
    private String pastEvents;
    @Value("${eventsPage.XPathUpcomingEventsCount}")
    private String upcomingEventsCount;
    @Value("${eventsPage.CSSCardBody}")
    private String cardBody;
    @Value("${eventsPage.XPathCardPlace}")
    private String cardPlace;
    @Value("${eventsPage.XPathCardLang}")
    private String cardLang;
    @Value("${eventsPage.XPathCardEvent}")
    private String cardEvent;
    @Value("${eventsPage.XPathCardDate}")
    private String cardDate;
    @Value("${eventsPage.XPathCardReg}")
    private String cardReg;
    @Value("${eventsPage.XPathCardSpeakers}")
    private String cardSpeakers;
    private List<WebElement> cards;
    private List<EventCard> eventCards = new ArrayList<>();

    public void openUpcomingEvents() {
        System.out.println(upcomingEvents);
        By upcomingEventsSelector = By.xpath(upcomingEvents + "/..");
        By upcomingEventsSelectorTop = By.xpath(upcomingEvents + "/..");
        if (!driver.findElement(upcomingEventsSelectorTop)
                .getAttribute("class")
                .contains("active")) {
            driver.findElement(upcomingEventsSelector).click();
        }
    }

    public boolean isCardApperance() {
        return elementIsPresent(By.cssSelector(cardBody));
    }

    public boolean isCounterCorrect(String buttonName) {
        int cardsFind = driver.findElements(By.cssSelector(cardBody)).size();
        int cardsInCounter = Integer.parseInt(driver
                .findElement(UniLoc.xpathLocator(UniLoc.EVENTCOUNTER, buttonName))
                .getText().trim());
        logger.info("Cards found " + cardsFind + " cards in counter " + cardsInCounter);
        return cardsFind == cardsInCounter;
    }

    public void getAllCards() {
        cards = driver.findElements(By.cssSelector(cardBody));
        logger.info("find " + cards.size() + " card(s)");
        for (WebElement card : cards) {
            EventCard eventCard = new EventCard();
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
            eventCards.add(eventCard);
            System.out.println(eventCard.getDate() + " - " + eventCard.getEventName() + " - " + eventCard.getPlace()
            + " - " + eventCard.getRegistration() + " - " + eventCard.getSpeakers());
        }
    }

    public boolean checkPlace() {
        boolean isOk = true;
        int counter = 1;
        for(WebElement card : cards) {
            try {
                String place = card.findElement(By.xpath(cardPlace))
                        .getText();
                logger.info("place for card number " + counter + " is " + ANSI_GREEN + place);
            } catch (NotFoundException e) {
                logger.info("place for card number " + counter + " not found");
                isOk = false;
            }
            counter++;
        }
        return isOk;
    }

    public boolean checkLang() {
        boolean isOk = true;
        int counter = 1;
        for(WebElement card : cards) {
            try {
                String lang = card.findElement(By.xpath(cardLang))
                        .getText();
                logger.info("language for card number " + counter + " is " + ANSI_GREEN + lang);
            } catch (NotFoundException e) {
                logger.info("language for card number " + counter + " not found");
                isOk = false;
            }
            counter++;
        }
        return isOk;
    }

    public boolean checkEvent() {
        boolean isOk = true;
        int counter = 1;
        for(WebElement card : cards) {
            try {
                String event = card.findElement(By.xpath(cardEvent))
                        .getText();
                logger.info("event for card number " + counter + " is " + ANSI_GREEN + event);
            } catch (NotFoundException e) {
                logger.info("event for card number " + counter + " not found");
                isOk = false;
            }
            counter++;
        }
        return isOk;
    }

    public boolean checkDate() {
        boolean isOk = true;
        int counter = 1;
        for(WebElement card : cards) {
            try {
                String date = card.findElement(By.xpath(cardDate))
                        .getText();
                logger.info("date for card number " + counter + " is " + ANSI_GREEN + date);
            } catch (NotFoundException e) {
                logger.info("date for card number " + counter + " not found");
                isOk = false;
            }
            counter++;
        }
        return isOk;
    }

    public boolean checkReg() {
        boolean isOk = true;
        int counter = 1;
        for(WebElement card : cards) {
            try {
                String reg = card.findElement(By.xpath(cardReg))
                        .getText();
                logger.info("registration for card number " + counter + " is " + ANSI_GREEN + reg);
            } catch (NotFoundException e) {
                logger.info("registration for card number " + counter + " not found");
                isOk = false;
            }
            counter++;
        }
        return isOk;
    }

    public boolean checkSpeakers() {
        boolean isOk = true;
        int counter = 1;
        for(WebElement card : cards) {
            try {
                String speakers = card.findElement(By.xpath(cardReg))
                        .getText();
                logger.info("speakers for card number " + counter + " is " + ANSI_GREEN + speakers);
            } catch (NotFoundException e) {
                logger.info("speakers for card number " + counter + " not found");
                isOk = false;
            }
            counter++;
        }
        return isOk;
    }

    public void openPastEvents() {
        By upcomingEventsSelector = By.xpath(pastEvents + "/..");
        By upcomingEventsSelectorTop = By.xpath(pastEvents + "/..");
        if (!driver.findElement(upcomingEventsSelectorTop)
                .getAttribute("class")
                .contains("active")) {
            driver.findElement(upcomingEventsSelector).click();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectFilterValue(String filter, String value) {
        //Запоминаем текущие элементы из списка тем
        List<WebElement> elements = driver.findElements(By.cssSelector(cardBody));
        driver.findElement(UniLoc.xpathLocator(UniLoc.SPAN, filter)).click();
        driver.findElement(UniLoc.xpathLocator(UniLoc.LABELDATA, value)).click();
        //ждём пока прогрузится новый список тем
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.invisibilityOf(elements.get(elements.size() - 1)));
        } catch (Exception e) {
            //do nothing
        }
    }

    public boolean isDateInCardLessCurrentDate() {

        for (EventCard card : eventCards) {
            String[] parseDate = card.getDate().split(" ");
            int year = Integer.parseInt(parseDate[parseDate.length - 1]);
            int month = Utils.parseMonth(parseDate[parseDate.length - 2]);
            int day = Integer.parseInt(parseDate[parseDate.length - 3]);
            LocalDate date = LocalDate.of(year, month, day);
            if (date.isAfter(LocalDate.now())) return false;
        }
        return true;
    }
}
