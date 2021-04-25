package finalProject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import wtf.uniloc.UniLoc;
import finalProject.common.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс для описания страницы - Events
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EpamEventsPage extends BasePage{

    //Локаторы
    private String upcomingEvents = "//span[contains(text(),'Upcoming events')]";
    private String pastEvents = "//span[contains(text(),'Past Events')]";
    private String cardBody = "div.evnt-event-card";
    private String upcomingEventsCount = "//span[contains(text(),'Upcoming events')]/../span[3]";
    private String cardPlace = "//*[@class='online' or @class='location']/span";
    private String cardLang = "//*[@class='evnt-details-cell language-cell']/p/span";
    private String cardEvent = "//*[@class='evnt-event-name']/h1/span";
    private String cardDate = "//*[@class='evnt-dates-cell dates']/p/span";
    private String cardReg = "//*[@class='evnt-dates-cell dates']/p/span[contains(@class, 'status')]";
    private String cardSpeakers = "//*[@class='evnt-people-cell speakers']/div/div[@class='evnt-speaker']";
    private String eventTitle = "//div[@class='evnt-event-name']/h1/span";

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
            logger.info("Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
            return true;
        } else {
            logger.warn("Найдено карточек - " + cardsFind + ", карточек в счётчике " + cardsInCounter);
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
                logger.info("Язык для карточки " + card.getCardLink()
                        + " - " + card.getLang());
            } else {
                logger.info("Язык для карточки  " + card.getCardLink()
                        + " - " + card.getLang());
            }
        }
    }

    public void checkEvent() {
        for(EventCard card : eventCards) {
            if (card.getEventName().equals("Not defined")) {
                logger.info("event for card " + card.getCardLink()
                        + " - " + card.getEventName());
            } else {
                logger.info("event for card " + card.getCardLink()
                        + " - " + card.getEventName());
            }
        }
    }

    public void checkDate() {
        for(EventCard card : eventCards) {
            if (card.getDate().equals("Not defined")) {
                logger.info("Дата для карточки " + card.getCardLink()
                        + " - " + card.getDate());
            } else {
                logger.info("Дата для карточки  " + card.getCardLink()
                        + " - " + card.getDate());
            }
        }
    }

    public void checkReg() {
        for(EventCard card : eventCards) {
            if (card.getRegistration().equals("Not defined")) {
                cardErrors.add(card.getCardLink());
                logger.info("регистрация для карточки " + card.getCardLink()
                        + " - " + card.getRegistration());
            } else {
                logger.info("регистрация для карточки  " + card.getCardLink()
                        + " - " + card.getRegistration());
            }
        }
    }

    public void checkSpeakers() {
        for(EventCard card : eventCards) {
            if (card.getSpeakers().equals("Not defined")) {
                logger.info("Спикеры для карточки " + card.getCardLink()
                        + " - " + card.getSpeakers());
            } else {
                logger.info("Спикеры для карточки  " + card.getCardLink()
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
                logger.warn("Карточка " + card.getEventName() + " с датой "
                        + date + " после текущей " + LocalDate.now());
                return false;
            } else {
                //Логируем
                logger.info("Карточка " + card.getEventName() + " с датой " + date
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
            logger.info("Карточки заполнены с ошибками");
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
                    logger.warn("Карточка " + card.getEventName() + " с датой "
                            + card.getDate() + " находится вне пределах текущей даты " + LocalDate.now());
                    return false;
                }
            }
            //Логируем
            logger.info("Карточка " + card.getEventName() + " с датой "
                    + card.getDate() + " в пределах от текущей даты " + LocalDate.now());
        }
        return true;
    }
}
