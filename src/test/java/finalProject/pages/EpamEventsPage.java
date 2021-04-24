package finalProject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.TimeoutException;
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

/**
 * Класс для описания страницы - Events
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
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
    private List<EventCard> eventCards = new ArrayList<>();
    private List<String> cardErrors = new ArrayList<>();

    public void openUpcomingEvents() {
        //Запоминаем текущий элемент из списка тем и значение
        SelenideElement element = $x(eventTitle);
        String elementText = element.getText();
        //Если раздел не активен, то перейти в него
        if (!$x(upcomingEvents + "/..")
                .getAttribute("class")
                .contains("active")) {
            $x(upcomingEvents + "/..").click();
            //ждём пока прогрузится новый список тем
            element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);
        }
        //Логируем
        logger.info("Переход в раздел Upcoming Events");
    }

    public boolean isCardApperance() {
        getAllCards();
        return $(cardBody).exists();
    }

    //Сделать регистронезависимый поиск для локатора
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
        logger.info("Найдено " + cards.size() + " карточек мероприятий");
        //Чистим карточки от предыдущих тестов
        eventCards = new ArrayList<>();
        //Парсим карточки событий
        for (SelenideElement card : cards) {
            EventCard eventCard = new EventCard();
            eventCard.parse(card.toWebElement());
            eventCards.add(eventCard);
        }
    }

    public void checkLang() {
        for(EventCard card : eventCards) {
            if (card.getLang().equals("Not defined")) {
                logger.info(Utils.ANSI_RED + "Язык для карточки " + card.getCardLink()
                        + " - " + card.getLang());
            } else {
                logger.info(Utils.ANSI_GREEN + "Язык для карточки  " + card.getCardLink()
                        + " - " + card.getLang());
            }
        }
    }

    public void checkEvent() {
        for(EventCard card : eventCards) {
            if (card.getEventName().equals("Not defined")) {
                logger.info(Utils.ANSI_RED + "event for card " + card.getCardLink()
                        + " - " + card.getEventName());
            } else {
                logger.info(Utils.ANSI_GREEN + "event for card " + card.getCardLink()
                        + " - " + card.getEventName());
            }
        }
    }

    public void checkDate() {
        for(EventCard card : eventCards) {
            if (card.getDate().equals("Not defined")) {
                logger.info(Utils.ANSI_RED + "Дата для карточки " + card.getCardLink()
                        + " - " + card.getDate());
            } else {
                logger.info(Utils.ANSI_GREEN + "Дата для карточки  " + card.getCardLink()
                        + " - " + card.getDate());
            }
        }
    }

    public void checkReg() {
        for(EventCard card : eventCards) {
            if (card.getRegistration().equals("Not defined")) {
                cardErrors.add(card.getCardLink());
                logger.info(Utils.ANSI_RED + "регистрация для карточки " + card.getCardLink()
                        + " - " + card.getRegistration());
            } else {
                logger.info(Utils.ANSI_GREEN + "регистрация для карточки  " + card.getCardLink()
                        + " - " + card.getRegistration());
            }
        }
    }

    public void checkSpeakers() {
        for(EventCard card : eventCards) {
            if (card.getSpeakers().equals("Not defined")) {
                logger.info(Utils.ANSI_RED + "Спикеры для карточки " + card.getCardLink()
                        + " - " + card.getSpeakers());
            } else {
                logger.info(Utils.ANSI_GREEN + "Спикеры для карточки  " + card.getCardLink()
                        + " - " + card.getSpeakers());
            }
        }
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
            //ждём пока прогрузится новый список тем
            element.waitUntil(Condition.not(Condition.matchesText(elementText)), 5000);
        }
        //Логируем
        logger.info("Переход в раздел Past Events");


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
            LocalDate date = Utils.parseEndDate(card.getDate());
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
        SelenideElement element = $(cardBody);
        String elementText = element.$("h1").getText();
        element.click();
        logger.info("Открываем карточку " + elementText);
    }

    public boolean isFieldFill() {
        if (cardErrors.size() > 0) {
            logger.info(Utils.ANSI_RED + "Карточки заполнены с ошибками");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkDateRange() {
        for (EventCard card : eventCards) {
            //Парсим дату
            LocalDate startDate = Utils.parseStartDate(card.getDate());
            LocalDate endDate = Utils.parseEndDate(card.getDate());
            LocalDate now = LocalDate.now();
            //Проверяем что дата в карточке в пределах диапазона или позже
            if (now.isBefore(startDate) || now.isAfter(endDate)) {
                if (!now.isBefore(endDate) || !now.isBefore(startDate)) {
                    //Логируем
                    logger.warn(Utils.ANSI_RED + "Карточка " + card.getEventName() + " с датой "
                            + card.getDate() + " находится вне пределах текущей даты " + LocalDate.now());
                    return false;
                }
            }
            //Логируем
            logger.info(Utils.ANSI_GREEN + "Карточка " + card.getEventName() + " с датой "
                    + card.getDate() + " в пределах от текущей даты " + LocalDate.now());
        }
        return true;
    }
}
