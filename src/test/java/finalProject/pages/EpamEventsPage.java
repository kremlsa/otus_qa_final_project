package finalProject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import finalProject.common.UniLoc;
import finalProject.common.Utils;
import io.cucumber.java.lv.Un;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

@Component
public class EpamEventsPage extends BasePage{

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
    @Value("${eventsPage.XPathEventTitle}")
    private String eventTitle;
    private List<SelenideElement> cards;
    private final List<EventCard> eventCards = new ArrayList<>();

    @Autowired
    EventCard eventCard;

    public void openUpcomingEvents() {
        //Запоминаем текущие элементы из списка тем
        List<WebElement> elements = driver.findElements(By.cssSelector(cardBody));
        //локатор для ссылки на раздел
        By upcomingEventsSelector = By.xpath(upcomingEvents + "/..");
        //Локатор для определения активности раздела
        By upcomingEventsSelectorTop = By.xpath(upcomingEvents + "/..");
        //Если раздел не активен, то перейти в него
        if (!driver.findElement(upcomingEventsSelectorTop)
                .getAttribute("class")
                .contains("active")) {
            driver.findElement(upcomingEventsSelector).click();
        }
        //ждём пока прогрузится новый список тем
        waitWhileDisappear(elements, 5);
    }

    public boolean isCardApperance() {
        return $(cardBody).exists();
    }

    public boolean isCounterCorrect(String buttonName) {
        //Карточек найдено
        int cardsFind = $$(cardBody).size();
        //Карточек в счётчике
        int cardsInCounter = Integer.parseInt($x(UniLoc.xpathString(UniLoc.EVENTCOUNTER, buttonName))
                .getText().trim());
        //Сравниваем карточки найденные и счётчик
        if (cardsFind == cardsInCounter) {
            logger.info(Utils.ANSI_GREEN + "Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
            return true;
        } else {
            logger.warn(Utils.ANSI_RED + "Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
            return false;
        }
    }

    public void getAllCards() {
        cards = $$(cardBody);
        logger.info("find " + cards.size() + " card(s)");
        for (SelenideElement card : cards) {
            eventCards.add(eventCard.parse(card));
        }
    }

    public boolean checkPlace() {
        boolean isOk = true;
        int counter = 1;
        for(WebElement card : cards) {
            try {
                String place = card.findElement(By.xpath(cardPlace))
                        .getText();
                logger.info("place for card number " + counter + " is " + Utils.ANSI_GREEN + place);
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
                logger.info("language for card number " + counter + " is " + Utils.ANSI_GREEN + lang);
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
                logger.info("event for card number " + counter + " is " + Utils.ANSI_GREEN + event);
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
                logger.info("date for card number " + counter + " is " + Utils.ANSI_GREEN + date);
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
                logger.info("registration for card number " + counter + " is " + Utils.ANSI_GREEN + reg);
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
                logger.info("speakers for card number " + counter + " is " + Utils.ANSI_GREEN + speakers);
            } catch (NotFoundException e) {
                logger.info("speakers for card number " + counter + " not found");
                isOk = false;
            }
            counter++;
        }
        return isOk;
    }

    public void openPastEvents() {
        //Запоминаем текущий элемент из списка тем и значение
        SelenideElement element = $x(eventTitle);
        String elementText = element.getText();
        //Если раздел не активен, то перейти в него
        if (!$x(pastEvents + "/..")
                .getAttribute("class")
                .contains("active")) {
            $x(pastEvents + "/..").click();
        }
        //Логируем
        logger.info("Переход в раздел Past Events");
        //ждём пока прогрузится новый список тем
        element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);

    }

    public void selectFilterValue(String filter, String value) {
        //Запоминаем текущий элемент из списка тем и значение
        SelenideElement element = $x(eventTitle);
        String elementText = element.getText();
        //Настраиваем элементы фильтра
        $x(UniLoc.xpathString(UniLoc.SPAN, filter)).click();
        $x(UniLoc.xpathString(UniLoc.LABELDATA, value)).click();
        //Логируем
        logger.info("Настроен фильтр " + filter + " со значением " + value);
        //ждём пока прогрузится новый список тем
        element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);
    }

    public boolean isDateInCardLessCurrentDate() {
        //Просматриваем все карточки
        for (EventCard card : eventCards) {
            //Парсим дату
            LocalDate date = Utils.parseDate(card.getDate());
            //Проверяем что дата в карточке до текущей
            if (date.isAfter(LocalDate.now())) {
                //Логируем
                logger.warn(Utils.ANSI_RED + "Карточка " + card.getEventName() + " с датой "
                        + date + " после текущей " + LocalDate.now());
                return false;
            } else {
                //Логируем
                logger.info(Utils.ANSI_GREEN + "Карточка " + card.getEventName() + " с датой " + date
                + " до текущей " + LocalDate.now());
            }
        }
        return true;
    }

    public void openAnyCard() {
        WebElement element = driver.findElement(By.cssSelector(cardBody));
        logger.info("Открываем карточку " + element.findElement(By.xpath(cardEvent)).getText());
        element.click();
    }

    /*public boolean checkUpcomingDate() {
        //Просматриваем все карточки
        for (EventCard card : eventCards) {
            //Парсим дату
            LocalDate date = Utils.parseDate(card.getDate());
            //Проверяем что дата в карточке после текущей но не более недели
            if (date.isBefore(LocalDate.now().with(DayOfWeek.SATURDAY).plusDays(1L)) || date.isBefore(LocalDate.now())) {
                //Логируем
                logger.warn(Utils.ANSI_RED + "Карточка " + card.getEventName() + " с датой "
                        + date + " находится вне пределах недели от текущей даты " + LocalDate.now());
                return false;
            } else {
                //Логируем
                logger.info(Utils.ANSI_GREEN + "Карточка " + card.getEventName() + " с датой " + date
                        + " в пределах недели от текущей дат " + LocalDate.now());
            }
        }
        return true;
    }*/
}
